package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
public class UserVersion extends AbstractEntity {

    @ManyToOne
    private User user;

    @Column(name = "CREATED")
    private Date created;

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
}
