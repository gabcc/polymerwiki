package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-13
 */

@Entity
public class Role implements Serializable {
    @Id
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    private List<UserVersion> users = new ArrayList<>();

    public Role() {
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

    public List<UserVersion> getUsers() {
        return users;
    }

    public void setUsers(List<UserVersion> users) {
        this.users = users;
    }
}
