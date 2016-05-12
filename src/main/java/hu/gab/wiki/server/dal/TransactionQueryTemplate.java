package hu.gab.wiki.server.dal;

import org.hibernate.Session;

/**
 * @author PG
 * @since 2016-03-14
 */
public interface TransactionQueryTemplate<T> {
    void execute(Session session, TransactionDBTemplate<T> transactionDBTemplate);
}
