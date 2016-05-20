package hu.gab.wiki.server.service;

import hu.gab.wiki.BaseServerTest;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class UserServiceTest extends BaseServerTest{

    private List<User> deletable = new ArrayList<>();

    @After
    public void afterTest() {
        new DBTemplate<Void>((session, template) -> {
            for (User user : deletable) {
                DAO_User.delete(session, user);
            }
        });

        deletable.clear();
    }

    @Test
    public void testNewUser() {
        final String name = "newUSerTest";
        final String email = "asdasdasdasdasda@asdasdasdsad3.com";
        final String password = "psd001";

        final String passwordHash = container.getUserService().createHash(password);

        User savedUser = container.getUserService().addNewUser(name, email, password);
        deletable.add(savedUser);

        if (!savedUser.getName().equals(name)) throw new RuntimeException("Nem egyezik a 2 objektum");
        if (!savedUser.getEmail().equals(email)) throw new RuntimeException("Nem egyezik a 2 objektum");
        if (!savedUser.getPasswordHash().equals(passwordHash)) throw new RuntimeException("Nem egyezik a 2 objektum");
    }

    /**
     * Ez valamiért nem tudja törölni az újként létrehozott usert egy foreign constraint miatt.
     */
    @Test
    public void testUpdateUser() {
        String nameOld = "nameOld";
        String emailOld = "emailOld@email.old";
        String passwordOld = "pwOld";

        final User user = container.getUserService().addNewUser(nameOld, emailOld, container.getUserService().createHash(passwordOld));

        DTO_User updatedUserDto = new DTO_User();
        updatedUserDto.setId(user.getId());
        updatedUserDto.setEmail(emailOld + "new");
        updatedUserDto.setName(nameOld + "new");
        updatedUserDto.setPassword("newPassword");

        User updated = container.getUserService().updateUser(updatedUserDto);
        deletable.add(updated);

        User felolvasott = new DBTemplate<User>((session, template) -> {
            session.clear();
            template.setResult(DAO_User.get(session, user.getId()));
        }).getResult();

        if (!felolvasott.getName().equals(nameOld + "new")) throw new RuntimeException("Nem ment le az update");
        if (!felolvasott.getEmail().equals(emailOld + "new")) throw new RuntimeException("Nem ment le az update");

        String pwHash = container.getUserService().createHash("newPassword");

        if (!felolvasott.getPasswordHash().equals(pwHash)) throw new RuntimeException("Nem ment le az update");
    }
}