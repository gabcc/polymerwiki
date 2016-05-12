package hu.gab.wiki.server.dal;


import org.hibernate.Session;

/**
 * @author PG
 * @since 2016-03-06
 */
public interface QueryTemplate<T> {
    void execute(Session session, DBTemplate<T> template);
}
