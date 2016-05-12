package hu.gab.wiki.server.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author PG
 * @since 2016-05-12
 */
@Entity
public class Comment extends AbstractEntity {

    @Column(name = "COMMENT_USER")
    private User user;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED")
    private Date created;

    @Enumerated(EnumType.ORDINAL)
    private ContentStatus status;

    @ManyToOne
    private Article article;
}
