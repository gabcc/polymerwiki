package hu.gab.wiki.server.exp;

import hu.gab.wiki.server.DTO;
import hu.gab.wiki.server.ServerCLC;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_Environment;
import hu.gab.wiki.server.dao.DAO_Role;
import hu.gab.wiki.server.entity.*;
import hu.gab.wiki.server.ioc.Container;
import hu.gab.wiki.server.service.AuthService;
import hu.gab.wiki.server.service.UserService;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.status.UserStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DBCreator {
    private static Container container;

    public static void main(String[] args) {
        createContainer();

        createDb();
//        testRead();

//        createUser();

        createRoles();
        createEnvironments();
        createDefaultAdmins();
    }

    private static void createContainer(){
        container = new Container();
        container.init();
    }

    private static void createDb() {
        new DBTemplate<Void>((session, template) -> {

        });
        System.out.println("DB creation ended");
    }

    private static void createRoles() {
        final Role admin = new Role();
        admin.setName("ADMIN");

        final Role editor = new Role();
        editor.setName("EDITOR");

        final Role visitor = new Role();
        visitor.setName("VISITOR");

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

    private static void createDefaultAdmins(){
        User user = container.getUserService().addNewUser("Percze Gábor", "g.percze@gmail.com", "123456");
        DTO_User dto_user = new DTO_User();
        dto_user.setEmail(user.getEmail());
        dto_user.setId(user.getId());
        dto_user.setName(user.getName());
        dto_user.setStatus(UserStatus.ACTIVE);

        List<DTO_Role> result = new DBTemplate<List<DTO_Role>>((session, template) -> {
            template.setResult(DAO_Role.list(session).stream().map(DTO.instance::dto).collect(Collectors.toList()));
        }).getResult();
        dto_user.setRoles(result);

        container.getUserService().updateUser(dto_user);
    }

    private static void createDefaultCategories(){
        Category category = new Category();

    }

    private static void createUser() {
        final User user = new User();
        user.setName("user1");
        user.setEmail("user1@asdsad.com");
        user.setPasswordHash(container.getUserService().createHash("user1"));
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
