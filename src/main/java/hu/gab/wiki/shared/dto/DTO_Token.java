package hu.gab.wiki.shared.dto;

import java.io.Serializable;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DTO_Token implements Serializable {
    private String token;

    public DTO_Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
