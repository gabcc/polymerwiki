package hu.gab.wiki.client.front.article;

import hu.gab.wiki.client.mvp.IWikiView;

/**
 * @author PG
 * @since 2016-05-15
 */
public interface IArticleView extends IWikiView<ArticlePresenter> {

    void setSlug(String slug);
}
