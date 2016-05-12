package hu.gab.wiki.server.entity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public class CategoryVersion extends AbstractEntity {
    @Column(name = "CREATED")
    private Date created;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<ArticleVersion> articleVersions = new ArrayList<>();
}
