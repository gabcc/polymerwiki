package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
@Entity
public class CategoryVersion implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<ArticleVersion> articleVersions = new ArrayList<>();

    public CategoryVersion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ArticleVersion> getArticleVersions() {
        return articleVersions;
    }

    public void setArticleVersions(List<ArticleVersion> articleVersions) {
        this.articleVersions = articleVersions;
    }
}
