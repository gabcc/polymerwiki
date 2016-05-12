package hu.gab.wiki.server.exp;

import hu.gab.wiki.server.dal.DBTemplate;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DBCreator {
    public static void main(String[] args) {
        new DBTemplate<Void>((session, template) -> {
            System.out.println("done");
        });
    }
}
