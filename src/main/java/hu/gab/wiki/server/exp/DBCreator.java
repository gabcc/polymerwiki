package hu.gab.wiki.server.exp;

import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_Environment;
import hu.gab.wiki.server.dao.DAO_Role;
import hu.gab.wiki.server.entity.Environment;
import hu.gab.wiki.server.entity.Role;
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

//        createUser();

        createRoles();
        createEnvironments();
    }

    private static void createDb() {
        new DBTemplate<Void>((session, template) -> {

        });
        System.out.println("megy");
    }

    private static void createRoles() {
        final Role admin = new Role();
        admin.setName("admin");

        final Role editor = new Role();
        editor.setName("editor");

        final Role visitor = new Role();
        visitor.setName("visitor");

        new DBTemplate<Void>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                DAO_Role.add(session1, admin);
                DAO_Role.add(session1, editor);
                DAO_Role.add(session1, visitor);
            });
        });
    }

    private static void createEnvironments() {
        final Environment devEnv = new Environment();
        devEnv.setName("dev");

        final Environment prodEnv = new Environment();
        prodEnv.setName("prod");

        new DBTemplate<Void>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                DAO_Environment.add(session1, devEnv);
                DAO_Environment.add(session1, prodEnv);
            });
        });
    }

    private static void createUser() {
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
