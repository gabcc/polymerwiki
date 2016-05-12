package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author PG
 * @since 2016-05-12
 */
@Entity
public class Environment extends AbstractEntity {

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
}
