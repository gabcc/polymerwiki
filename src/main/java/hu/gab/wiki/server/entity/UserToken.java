package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author PG
 * @since 2016-05-15
 */
@Entity
public class UserToken implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "LAST_ACTION")
    private Date lastAction;

    @Column(name = "EXPIRED")
    private boolean expired;

    public UserToken() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastAction() {
        return lastAction;
    }

    public void setLastAction(Date lastAction) {
        this.lastAction = lastAction;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
