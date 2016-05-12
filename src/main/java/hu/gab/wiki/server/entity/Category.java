package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
public class Category extends AbstractEntity {

    @Column(name = "SLUG", unique = true)
    private String slug;

    @ManyToOne
    private Environment environment;

    @OneToMany(mappedBy = "category")
    private List<CategoryVersion> versions = new ArrayList<>();

    public Category() {
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
