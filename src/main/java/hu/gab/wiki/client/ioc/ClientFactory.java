package hu.gab.wiki.client.ioc;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import hu.gab.wiki.client.admin.article.IArticleAdminView;
import hu.gab.wiki.client.admin.user.IUserAdminView;
import hu.gab.wiki.client.drawer.DrawerManager;
import hu.gab.wiki.client.front.about.IAboutView;
import hu.gab.wiki.client.front.article.IArticleView;
import hu.gab.wiki.client.front.home.IHomeView;
import hu.gab.wiki.client.store.ClientStore;

/**
 * @author PG
 * @since 2016-04-30
 */
public interface ClientFactory {
    EventBus getEventBus();

    PlaceController getPlaceController();

    IAboutView getAboutView();

    IHomeView getHomeView();

    IArticleView getArticleView();

    IUserAdminView getUserAdminView();

    IArticleAdminView getArticleAdminView();

    DrawerManager getDrawerManager();

    ClientStore getClientStore();
}
