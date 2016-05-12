package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-11
 */

@Entity
public class User extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "CREATED")
    private Date created;

    @OneToMany(mappedBy = "user")
    private List<UserVersion> versions = new ArrayList<>();

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<UserVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<UserVersion> versions) {
        this.versions = versions;
    }
}
