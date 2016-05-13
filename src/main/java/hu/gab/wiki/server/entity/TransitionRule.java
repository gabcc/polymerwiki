package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author PG
 * @since 2016-05-12
 */
public class TransitionRule implements Serializable {
    @Id
    private long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToOne
    private Environment from;

    @ManyToOne
    private Environment to;

    public TransitionRule() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Environment getFrom() {
        return from;
    }

    public void setFrom(Environment from) {
        this.from = from;
    }

    public Environment getTo() {
        return to;
    }

    public void setTo(Environment to) {
        this.to = to;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
