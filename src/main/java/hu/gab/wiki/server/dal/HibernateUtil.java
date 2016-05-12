package hu.gab.wiki.server.dal;

import com.sun.istack.internal.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author PG
 * @since 2016-03-06
 */
public class HibernateUtil {

    private static final Logger logger = Logger.getLogger(HibernateUtil.class);

    private static final HibernateUtil instance = new HibernateUtil();

    private HibernateUtil(){
    }
    private SessionFactory sessionFactory;

    public static HibernateUtil getInstance() {
        return instance;
    }

    public synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            logger.info("New sessionfactory created.");

            // loads configuration and mappings
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }

        return sessionFactory;
    }
}
