package hu.gab.wiki.server.dal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

/**
 * @author PG
 * @since 2016-03-06
 */
public class HibernateUtil {

    private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());

    private static final HibernateUtil instance = new HibernateUtil();

    private HibernateUtil() {
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
