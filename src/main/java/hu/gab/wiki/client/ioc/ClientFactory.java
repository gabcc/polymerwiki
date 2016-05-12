package hu.gab.wiki.client.ioc;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import hu.gab.wiki.client.about.IAboutView;
import hu.gab.wiki.client.drawer.DrawerManager;
import hu.gab.wiki.client.home.IHomeView;

/**
 * @author PG
 * @since 2016-04-30
 */
public interface ClientFactory {
    EventBus getEventBus();

    PlaceController getPlaceController();

    IAboutView getAboutView();

    IHomeView getHomeView();

    DrawerManager getDrawerManager();
}
