package hu.gab.wiki.client.about;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;

/**
 * @author PG
 * @since 2016-04-30
 */
public class AboutActivity extends WikiActivity<IAboutView> implements AboutPresenter {

    public AboutActivity(ClientFactory clientFactory, IAboutView view) {
        super(clientFactory, view);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.makeWidgetSetItself(panel);
    }
}
