package hu.gab.wiki.server.exp;

import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.User;

/**
 * @author PG
 * @since 2016-05-15
 */
public class Exp {
    public static void main(String[] args) {
        new DBTemplate<Void>((session, template) -> {
            User user = DAO_User.list(session).get(0);

            session.delete(user);
        });
    }
}
