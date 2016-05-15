package hu.gab.wiki.client.front.about;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.shared.FrontRole;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
public class AboutActivity extends WikiActivity<IAboutView> implements AboutPresenter {

    public AboutActivity(ClientFactory clientFactory, IAboutView view) {
        super(clientFactory, view);
    }

    @Override
    public void afterStart(AcceptsOneWidget panel, EventBus eventBus) {

    }

    @Override
    public void addNewUser(String name) {
    }
}
