package hu.gab.wiki.server.service;

import hu.gab.wiki.BaseServerTest;
import hu.gab.wiki.server.DTO;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.dao.DAO_UserToken;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-19
 */
public class AuthServiceTest extends BaseServerTest {

    List<User> users = new ArrayList<>();

    @After
    public void deleteStuff() {
        new DBTemplate<Void>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                for (User user : users) {
                    DAO_User.delete(session1, user);
                }

                DAO_UserToken.testDeleteAll(session1);
            });
        });
    }

    @Test
    public void testLogin() {
        //Helyes login
        User testUser = createTestUser();
        DTO_Token login = container.getAuthService().login(testUser.getEmail(), "123456");

        users.add(testUser);

        //Hibás login
        boolean hibarafut = false;
        try {
            container.getAuthService().login(testUser.getEmail(), "ezegyrosszpassword");
        } catch (Throwable t) {
            hibarafut = true;
        }

        if (hibarafut == false) throw new RuntimeException("Nem futott hibára hibás pw esetén!");
    }

    @Test
    public void testActiveToken() {
        User testUser = createTestUser();
        users.add(testUser);

        DTO_User dtoUser = DTO.instance.dto(testUser);

        DTO_Token token = container.getAuthService().login(testUser.getEmail(), "123456");
        boolean tokenValid = container.getAuthService().isTokenValid(token, dtoUser);
        if (tokenValid == false) throw new RuntimeException("Nem valid a helyesen bejelentkezett user token!");
        container.getAuthService().logout(token, dtoUser);

        boolean tokenValid1 = container.getAuthService().isTokenValid(token, dtoUser);
        if (tokenValid1 == true) throw new RuntimeException("Elfogadta a már lejárt tokent!");
    }

    private User createTestUser() {
        User authtestuser = container.getUserService().addNewUser("authtestuser", "authtestuser@asd.com", "123456");
        return authtestuser;
    }
}