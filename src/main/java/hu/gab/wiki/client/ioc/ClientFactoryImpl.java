package hu.gab.wiki.client.ioc;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import hu.gab.wiki.client.admin.article.ArticleAdminView;
import hu.gab.wiki.client.admin.article.IArticleAdminView;
import hu.gab.wiki.client.admin.user.IUserAdminView;
import hu.gab.wiki.client.admin.user.UserAdminView;
import hu.gab.wiki.client.drawer.DrawerManager;
import hu.gab.wiki.client.front.about.AboutView;
import hu.gab.wiki.client.front.about.IAboutView;
import hu.gab.wiki.client.front.article.ArticleView;
import hu.gab.wiki.client.front.article.IArticleView;
import hu.gab.wiki.client.front.home.HomeView;
import hu.gab.wiki.client.front.home.IHomeView;
import hu.gab.wiki.client.store.ClientStore;

/**
 * @author PG
 * @since 2016-04-30
 */
public class ClientFactoryImpl implements ClientFactory {
    private EventBus eventBus = new SimpleEventBus();
    private PlaceController placeController = new PlaceController(eventBus);
    private DrawerManager drawerManager = null;

    private HomeView homeView;
    private AboutView aboutView;
    private ArticleView articleView;

    private ClientStore clientStore = new ClientStore();

    /**
     * Admin feluletek
     */
    private UserAdminView userAdminView;
    private ArticleAdminView articleAdminView;


    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public IAboutView getAboutView() {
        if (aboutView == null) {
            aboutView = new AboutView();
        }
        return aboutView;
    }

    @Override
    public IHomeView getHomeView() {
        if (homeView == null) {
            homeView = new HomeView();
        }
        return homeView;
    }

    @Override
    public IUserAdminView getUserAdminView() {
        if (userAdminView == null) {
            userAdminView = new UserAdminView();
        }
        return userAdminView;
    }

    @Override
    public IArticleAdminView getArticleAdminView() {
        if (articleAdminView == null) {
            articleAdminView = new ArticleAdminView();
        }
        return articleAdminView;
    }

    @Override
    public IArticleView getArticleView() {
        if (articleView == null) {
            articleView = new ArticleView();
        }
        return articleView;
    }

    @Override
    public DrawerManager getDrawerManager() {
        return drawerManager;
    }

    @Override
    public ClientStore getClientStore() {
        return clientStore;
    }


    public void setDrawerManager(DrawerManager drawerManager) {
        this.drawerManager = drawerManager;
    }
}
