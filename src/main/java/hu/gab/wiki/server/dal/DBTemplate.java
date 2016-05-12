package hu.gab.wiki.server.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PG
 * @since 2016-03-06
 */
public class DBTemplate<T> {
    private T result;

    public DBTemplate(QueryTemplate<T> queryTemplate) {

        HibernateUtil instance = HibernateUtil.getInstance();
        SessionFactory sessionFactory = instance.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            queryTemplate.execute(session, this);
        }
        finally {
            session.flush();
            session.close();
        }
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
