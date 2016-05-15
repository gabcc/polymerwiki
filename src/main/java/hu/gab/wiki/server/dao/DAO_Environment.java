package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.Environment;
import org.hibernate.Session;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DAO_Environment {
    public static void add(Session session, Environment environment){
        session.save(environment);
    }
}
