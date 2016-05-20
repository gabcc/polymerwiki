package hu.gab.wiki.client.admin.article;

import hu.gab.wiki.client.mvp.IWikiView;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public interface IArticleAdminView extends IWikiView<ArticleAdminPresenter> {

    void setArticleSummaries(List<DTO_ArticleSummary> articleSummaryList);
}
