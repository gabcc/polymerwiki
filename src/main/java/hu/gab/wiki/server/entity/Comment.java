package hu.gab.wiki.server.entity;

import hu.gab.wiki.shared.status.ContentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author PG
 * @since 2016-05-12
 */
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED")
    private Date created;

    @Enumerated(EnumType.ORDINAL)
    private ContentStatus status;

    @ManyToOne
    private Article article;

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
