package hu.gab.wiki.client.home;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;

/**
 * @author PG
 * @since 2016-04-30
 */
public class HomeActivity extends WikiActivity<IHomeView> implements HomePresenter {

    public HomeActivity(ClientFactory clientFactory, IHomeView view) {
        super(clientFactory, view);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.makeWidgetSetItself(panel);
    }

}
