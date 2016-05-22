package hu.gab.wiki.shared.dto.useradmin;

import hu.gab.wiki.shared.DTO_Base;
import hu.gab.wiki.shared.status.UserStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DTO_User implements DTO_Base {
    private long id;

    private String name;
    private String email;
    private String password;
    private UserStatus status;

    private List<DTO_Role> roles = new ArrayList<>();

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

    public List<DTO_Role> getRoles() {
        return roles;
    }

    public void setRoles(List<DTO_Role> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DTO_User dto_user = (DTO_User) o;

        return id == dto_user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
