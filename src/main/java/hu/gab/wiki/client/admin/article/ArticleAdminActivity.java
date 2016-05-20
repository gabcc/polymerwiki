package hu.gab.wiki.client.admin.article;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.vaadin.polymer.paper.widget.PaperInput;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.client.admin.article.widget.CreateArticleWidget;
import hu.gab.wiki.client.helper.CommonWikiAsyncHandler;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticleAdminActivity extends WikiActivity<IArticleAdminView> implements ArticleAdminPresenter {
    public ArticleAdminActivity(ClientFactory clientFactory, IArticleAdminView view) {
        super(clientFactory, view);
    }

    @Override
    public void afterStart(AcceptsOneWidget panel, EventBus eventBus) {

    }

    @Override
    public void triggerCreateArticleModal() {
        CreateArticleWidget createArticleWidget = new CreateArticleWidget(this);
        createArticleWidget.show();
    }

    @Override
    public void onCreateArticle(final CreateArticleWidget widget) {
        PaperInput articleName = widget.getArticleName();
        PaperInput articleSlug = widget.getArticleSlug();

        DTO_ArticleSummary dto_articleSummary = new DTO_ArticleSummary();
        dto_articleSummary.setCurrentName(articleName.getValue());
        dto_articleSummary.setSlug(articleSlug.getValue());

        AppUtils.showLoadingSpinner();
        WikiService.App.getInstance().addArticle(AppUtils.getAuthData(), dto_articleSummary, new CommonWikiAsyncHandler<Void>() {
            @Override
            public void onSuccess(Void result) {
                AppUtils.hideLoadingSpinner();
                refreshArticles();
                widget.hide();
            }
        });
    }

    public void refreshArticles(){
        WikiService.App.getInstance().listArticleSummaries(new CommonWikiAsyncHandler<List<DTO_ArticleSummary>>() {
            @Override
            public void onSuccess(List<DTO_ArticleSummary> result) {
                view.setArticleSummaries(result);
            }
        });
    }
}
