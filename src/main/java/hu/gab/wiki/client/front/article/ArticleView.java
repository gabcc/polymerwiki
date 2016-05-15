package hu.gab.wiki.client.front.article;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import hu.gab.wiki.client.mvp.WikiView;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticleView extends WikiView<ArticlePresenter> implements IArticleView {

    @UiField
    HTMLPanel articleContainer;

    @Override
    protected void afterActivitySet() {

    }

    @Override
    public void setSlug(String slug) {
        articleContainer.clear();

        if(slug.equals("")){
            slug = "no slug";
        }

        articleContainer.add(new Label(slug));
    }

    interface ArticleViewUiBinder extends UiBinder<Widget, ArticleView> {
    }

    private static ArticleViewUiBinder ourUiBinder = GWT.create(ArticleViewUiBinder.class);

    public ArticleView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}