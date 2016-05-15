package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.UserVersion;
import org.hibernate.Session;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DAO_UserVersion {
    public static void add(Session session, UserVersion userVersion){
        session.save(userVersion);
    }
}
