package hu.gab.wiki.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.service.AuthService;
import hu.gab.wiki.server.service.UserService;
import hu.gab.wiki.shared.dto.DTO_LoginData;
import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.exceptions.CommonWikiException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-12
 */
public class WikiServiceImpl extends RemoteServiceServlet implements WikiService {
    @Override
    public List<DTO_User> listUsers() {
        List<DTO_User> users = new DBTemplate<List<DTO_User>>((session, template) -> {
            List<User> tempUsers = DAO_User.list(session);
            template.setResult(
                    tempUsers.stream().map(DTO.instance::dto).collect(Collectors.toList())
            );
        }).getResult();

        return users;
    }

    @Override
    public void addNewUser(String name, String email, String password) throws CommonWikiException {
        if (name == null || email == null || password == null) {
            throw new RuntimeException("Nincs megadva egy szükséges elem!");
        }

        try {
            UserService.instance.addNewUser(name, email, password);
        } catch (Throwable r) {
            throw new CommonWikiException(r.getMessage());
        }

    }

    @Override
    public void updateUser(DTO_User user) throws CommonWikiException {
        UserService.instance.updateUser(user);
    }

    @Override
    public List<DTO_Role> getRoles() {
        return UserService.instance.getRoles();
    }

    @Override
    public DTO_LoginData login(final String email, final String password) throws CommonWikiException {
        if (email == null || password == null) {
            throw new CommonWikiException("Nincs megadva vagy az email vagy a password");
        }

        try {
            DTO_Token token = AuthService.instance.login(email, password);
            DTO_User user = new DBTemplate<DTO_User>((session, template) -> {
                template.setResult(
                        DTO.instance.dto(DAO_User.findByEmail(session, email))
                );
            }).getResult();

            DTO_LoginData loginData = new DTO_LoginData();
            loginData.setUser(user);
            loginData.setToken(token);

            return loginData;
        } catch (Throwable t) {
            throw new CommonWikiException(t.getMessage());
        }
    }
}