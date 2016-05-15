package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.Role;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DAO_Role {
    public static List<Role> list(Session session) {
        return (List<Role>) session.createCriteria(Role.class).list();
    }

    public static void add(Session session, Role role) {
        session.save(role);
    }

    public static List<Role> list(Session session, Collection<Long> ids) {
        if(ids.size() > 0){
            return (List<Role>) session.createCriteria(Role.class).add(Restrictions.in("id", ids)).list();
        }
        else{
            return new ArrayList<>();
        }
    }
}
