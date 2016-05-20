package hu.gab.wiki.client.admin.article;

import hu.gab.wiki.client.admin.article.widget.CreateArticleWidget;
import hu.gab.wiki.client.mvp.IWikiPresenter;

/**
 * @author PG
 * @since 2016-05-15
 */
public interface ArticleAdminPresenter extends IWikiPresenter<IArticleAdminView> {
    void triggerCreateArticleModal();

    void onCreateArticle(CreateArticleWidget widget);

    void refreshArticles();
}
