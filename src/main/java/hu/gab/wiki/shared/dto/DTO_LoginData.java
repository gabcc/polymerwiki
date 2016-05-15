package hu.gab.wiki.shared.dto;

import hu.gab.wiki.shared.DTO_Base;
import hu.gab.wiki.shared.FrontRole;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DTO_LoginData implements DTO_Base {
    private DTO_Token token;
    private DTO_User user;

    public DTO_LoginData() {
    }

    public DTO_Token getToken() {
        return token;
    }

    public void setToken(DTO_Token token) {
        this.token = token;
    }

    public DTO_User getUser() {
        return user;
    }

    public void setUser(DTO_User user) {
        this.user = user;
    }
}
