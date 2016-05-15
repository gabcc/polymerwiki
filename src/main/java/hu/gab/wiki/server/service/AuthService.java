package hu.gab.wiki.server.service;

import hu.gab.wiki.server.DTO;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.dao.DAO_UserToken;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserToken;
import hu.gab.wiki.shared.dto.DTO_Token;

/**
 * @author PG
 * @since 2016-05-15
 */
public class AuthService {

    public static final AuthService instance = new AuthService();

    public DTO_Token login(String email, String password) {
        DTO_Token resultToken = new DBTemplate<DTO_Token>((session, template) -> {
            new TransactionDBTemplate<DTO_Token>(session, (session1, transactionDBTemplate) -> {
                User byEmail = DAO_User.findByEmail(session1, email);
                if (byEmail == null) {
                    throw new RuntimeException("Hibás email cím vagy jelszó");
                }

                String hash = UserService.instance.createHash(password);
                if (hash.equals(byEmail.getPasswordHash())) {
                    DAO_UserToken.clearPrevious(session1, byEmail);
                    UserToken token = DAO_UserToken.createToken(session1, byEmail);
                    template.setResult(DTO.instance.dto(token));
                } else {
                    throw new RuntimeException("Hibás email cím vagy jelszó");
                }
            });
        }).getResult();

        return resultToken;
    }
}
