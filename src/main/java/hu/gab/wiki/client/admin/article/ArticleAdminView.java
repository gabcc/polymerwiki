package hu.gab.wiki.client.admin.article;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import hu.gab.wiki.client.mvp.WikiView;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticleAdminView extends WikiView<ArticleAdminPresenter> implements IArticleAdminView {
    @Override
    protected void afterActivitySet() {

    }

    interface ArticleAdminActivityUiBinder extends UiBinder<Widget, ArticleAdminView> {
    }

    private static ArticleAdminActivityUiBinder ourUiBinder = GWT.create(ArticleAdminActivityUiBinder.class);

    public ArticleAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}