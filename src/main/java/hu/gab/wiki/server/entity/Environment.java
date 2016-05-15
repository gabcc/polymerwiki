package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author PG
 * @since 2016-05-12
 */
@Entity
public class Environment implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME")
    private String name;

    public Environment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
