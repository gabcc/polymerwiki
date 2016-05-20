package hu.gab.wiki.server;

import hu.gab.wiki.shared.RoleEntityName;

import java.util.Arrays;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-19
 */
public class ServerCLC {
    public static class UserToken{
        //Token lejárati idő másodpercben
        public static final int EXPIRATION_TIME = 5000;
    }

    public static class Auth{
        public static final List<String> LIST_USERS_ROLES = Arrays.asList(RoleEntityName.ADMIN);
        public static final List<String> ADD_UPDATE_USER_ROLES = Arrays.asList(RoleEntityName.ADMIN);

        public static final List<String> ADD_UPDATE_ARTICLES = Arrays.asList(RoleEntityName.ADMIN);
    }
}
