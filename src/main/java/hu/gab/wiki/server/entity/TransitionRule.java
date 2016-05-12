package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 * @author PG
 * @since 2016-05-12
 */
public class TransitionRule extends AbstractEntity {
    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToOne
    private Environment from;

    @ManyToOne
    private Environment to;
}
