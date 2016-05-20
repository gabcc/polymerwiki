package hu.gab.wiki.shared.dto.articleadmin;

import hu.gab.wiki.shared.DTO_Base;

/**
 * @author PG
 * @since 2016-05-20
 */
public class DTO_ArticleSummary implements DTO_Base {
    private long id;
    private String slug;
    private String currentName;

    public DTO_ArticleSummary() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
}
