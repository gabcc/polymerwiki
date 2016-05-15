package hu.gab.wiki.client.store;

import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ClientStore {
    private DTO_User user;
    private DTO_Token token;

    public ClientStore() {
    }

    public DTO_User getUser() {
        return user;
    }

    public void setUser(DTO_User user) {
        this.user = user;
    }

    public DTO_Token getToken() {
        return token;
    }

    public void setToken(DTO_Token token) {
        this.token = token;
    }
}
