package hu.gab.wiki.client.admin.article;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;

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
}
