package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
public class Article extends AbstractEntity {

    @Column(name = "SLUG", unique = true)
    private String slug;

    @Column(name = "CREATED")
    private Date created;

    @ManyToOne
    private Environment environment;

    @Enumerated(EnumType.ORDINAL)
    private ContentStatus status;

    @OneToMany(mappedBy = "article")
    private List<ArticleVersion> versions = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    public Article() {
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    public List<ArticleVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<ArticleVersion> versions) {
        this.versions = versions;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
