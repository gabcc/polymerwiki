package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
public class ArticleVersion implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Article article;

    public ArticleVersion() {
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
