package hu.gab.wiki.server.service;

import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserVersion;

import java.util.Date;

/**
 * @author PG
 * @since 2016-05-11
 */
public class UserService {
    public static String createHash(String password){
        return password;
    }

    public static void addNewUser(final String name, final String email, final String password){
        new DBTemplate<Void>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                User userByMail = DAO_User.findByEmail(session, email);
                if(userByMail == null){
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPasswordHash(createHash(password));
                    user.setCreated(new Date());

                    UserVersion userVersion = new UserVersion();
                    userVersion.setUser(user);
                    userVersion.setCreated(new Date());
//
                    session.save(user);
                }
                else {
                    throw new RuntimeException("Van már user ilyen email címmel.");
                }
            });
        });
    }
}
