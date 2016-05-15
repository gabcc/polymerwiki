package hu.gab.wiki.server.dal;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.logging.Logger;

/**
 * @author PG
 * @since 2016-03-14
 * Tranzakcionalis dbtemplate
 */
public class TransactionDBTemplate<T> {
    private static Logger logger = Logger.getLogger(TransactionDBTemplate.class.getName());

    public TransactionDBTemplate(Session session, TransactionQueryTemplate<T> queryTemplate) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            queryTemplate.execute(session, this);
            transaction.commit();
        } catch (Throwable t) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.warning(t.getMessage());
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }
}
