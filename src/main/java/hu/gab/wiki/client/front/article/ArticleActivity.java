package hu.gab.wiki.client.front.article;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;

import java.util.logging.Logger;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticleActivity extends WikiActivity<IArticleView> implements ArticlePresenter {
    private static final Logger logger = Logger.getLogger(ArticleActivity.class.getName());

    public ArticleActivity(ClientFactory clientFactory, IArticleView view) {
        super(clientFactory, view);
    }

    @Override
    public void afterStart(AcceptsOneWidget panel, EventBus eventBus) {
        Place where = clientFactory.getPlaceController().getWhere();
        if (!(where instanceof ArticlePlace)) {
            logger.warning("Helytelen place-t kaptunk...");
            clientFactory.getPlaceController().goTo(new ArticlePlace());
        } else {
            ArticlePlace articlePlace = (ArticlePlace) where;
            view.setSlug(articlePlace.getArticleSlug());
        }
    }
}
