package hu.gab.wiki.shared.dto;

import hu.gab.wiki.shared.DTO_Base;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DTO_UserVersion implements DTO_Base {
    private DTO_User user;
    private Date created;
    private List<DTO_Role> roles = new ArrayList<>();

    public DTO_UserVersion() {
    }

    public DTO_User getUser() {
        return user;
    }

    public void setUser(DTO_User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<DTO_Role> getRoles() {
        return roles;
    }

    public void setRoles(List<DTO_Role> roles) {
        this.roles = roles;
    }
}
