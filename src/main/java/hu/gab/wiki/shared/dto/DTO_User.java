package hu.gab.wiki.shared.dto;

import hu.gab.wiki.shared.DTO_Base;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DTO_User implements DTO_Base {
    private long id;

    private String name;

    private String email;

    private String password;

    public DTO_User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
