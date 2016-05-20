package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
public class UserVersion implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "CREATED")
    private Date created;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

    public UserVersion() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
