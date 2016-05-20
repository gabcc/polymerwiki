package hu.gab.wiki.server.service;

import com.google.inject.Inject;
import hu.gab.wiki.server.DTO;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.dao.DAO_UserToken;
import hu.gab.wiki.server.dao.DAO_UserVersion;
import hu.gab.wiki.server.entity.Role;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserToken;
import hu.gab.wiki.server.entity.UserVersion;
import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.helper.CommonAuthHelper;

import java.util.Date;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * @author PG
 * @since 2016-05-15
 */
public class AuthService {
    private UserService userService;

    @Inject
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public DTO_Token login(String email, String password) {
        DTO_Token resultToken = new DBTemplate<DTO_Token>((session, template) -> {
            new TransactionDBTemplate<DTO_Token>(session, (session1, transactionDBTemplate) -> {
                User byEmail = DAO_User.findByEmail(session1, email);
                if (byEmail == null) {
                    throw new RuntimeException("Hibás email cím vagy jelszó");
                }

                String hash = userService.createHash(password);
                if (hash.equals(byEmail.getPasswordHash())) {
                    DAO_UserToken.clearPrevious(session1, byEmail);
                    UserToken token = DAO_UserToken.createToken(session1, byEmail, userService);
                    template.setResult(DTO.instance.dto(token));
                } else {
                    throw new RuntimeException("Hibás email cím vagy jelszó");
                }
            });
        }).getResult();

        return resultToken;
    }

    public void logout(DTO_Token userToken, DTO_User user){
        if(isTokenValid(userToken, user)){
            new DBTemplate<Void>((session, template) -> {
                new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                    User userEntity = DAO_User.get(session1, user.getId());

                    DAO_UserToken.clearPrevious(session1, userEntity);
                });
            });
        }
    }

    /**
     * Megnézi, hogy az adott token valid-e és még aktív-e.
     * Mellékhatásként refreshel is rajta egyet, azaz további expiration ideig jó lesz.
     * @param token
     * @param user
     * @return
     */
    public boolean isTokenValid(DTO_Token token, final DTO_User user) {
        Boolean result = new DBTemplate<Boolean>((session, template) -> {
            new TransactionDBTemplate<Boolean>(session, (session1, transactionDBTemplate) -> {
                User userByEmail = DAO_User.findByEmail(session1, user.getEmail());
                if (user == null) {
                    template.setResult(false);
                } else {
                    UserToken activeToken = DAO_UserToken.getActiveToken(session1, userByEmail);
                    if(activeToken == null){
                        template.setResult(false);
                    }
                    else{
                        if(token.getToken().equals(activeToken.getToken())){
                            DAO_UserToken.refreshToken(session1, activeToken);
                            template.setResult(true);
                        }
                        else{
                            template.setResult(false);
                        }
                    }
                }
            });
        }).getResult();

        return result;
    }

    public boolean isAuthorized(final List<String> requiredRoles, DTO_User user){
        Boolean result = new DBTemplate<Boolean>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                User userEntity = DAO_User.get(session1, user.getId());
                List<UserVersion> versions = userEntity.getVersions();

                DTO_User dto = DTO.instance.dto(userEntity);
                List<String> rolesOfUser = CommonAuthHelper.getRolesOfUser(dto);

                template.setResult(CommonAuthHelper.isEligibleForAction(requiredRoles, rolesOfUser));
            });
        }).getResult();

        return result;
    }
}
