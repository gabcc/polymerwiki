package hu.gab.wiki.server.exp;

import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DBCreator {
    public static void main(String[] args) {
        createDb();
//        testRead();

        createUser();
    }

    private static void createDb(){
        new DBTemplate<Void>((session, template) -> {

        });
        System.out.println("megy");
    }

    private static void createUser(){
        final User user = new User();
        user.setName("user1");
        user.setEmail("user1@asdsad.com");
        user.setPasswordHash(UserService.createHash("user1"));
        user.setCreated(new Date());

        new DBTemplate<Void>((session, template) -> {
            session.save(user);
        });

        System.out.println("User saved with id: " + user.getId());
    }

    private static void testRead() {
        new DBTemplate<Void>((session, template) -> {
            List<User> list = (List<User>) session.createCriteria(User.class).list();

            for (User iter : list) {
                System.out.println(iter.getId() + " -->" + iter.getName());
            }
        });
    }
}
