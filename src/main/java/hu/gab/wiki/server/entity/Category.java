package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CategoryVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<CategoryVersion> versions) {
        this.versions = versions;
    }
}
