package hu.gab.wiki.server.entity;

import javax.persistence.*;
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
    @GeneratedValue
    private long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserVersion> userVersions = new ArrayList<>();

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

    public List<UserVersion> getUserVersions() {
        return userVersions;
    }

    public void setUserVersions(List<UserVersion> users) {
        this.userVersions = users;
    }
}
