package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-11
 */
public class DAO_User {
    public static User get(Session session, long id) {
        List users = session.createCriteria(User.class).
                add(Restrictions.eq("id", id)).list();

        if (users.size() > 1) {
            throw new RuntimeException("Nagy a baj");
        } else if(users.size() == 1){
            return (User) users.get(0);
        }
        else{
            return null;
        }
    }

    public static List<User> findByName(Session session, String name) {
        List users = session.createCriteria(User.class).
                add(Restrictions.eq("name", name)).list();

        return (List<User>) users;
    }

    public static User findByEmail(Session session, String email) {
        List users = session.createCriteria(User.class).
                add(Restrictions.eq("email", email)).list();

        if (users.size() > 1) {
            throw new RuntimeException("Több ugyanolyan emailünk van! ez baj!");
        }
        else if(users.size() == 1){
            return ((List<User>) users).get(0);
        }
        else{
            return null;
        }
    }

    public static List<User> list(Session session) {
        List users = session.createCriteria(User.class).list();
        return (List<User>) users;
    }

    public static void add(Session session, User user) {
        session.save(user);
    }
}
