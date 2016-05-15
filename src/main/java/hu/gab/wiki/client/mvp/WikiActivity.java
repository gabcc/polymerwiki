package hu.gab.wiki.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.shared.FrontRole;
import hu.gab.wiki.client.ioc.ClientFactory;

import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
abstract public class WikiActivity<T extends IWikiView> extends AbstractActivity implements IWikiPresenter<T> {
    protected ClientFactory clientFactory;
    protected T view;

    public WikiActivity(ClientFactory clientFactory, T view) {
        this.clientFactory = clientFactory;
        this.view = view;

        view.setActivity(this);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.makeWidgetSetItself(panel);
        afterStart(panel, eventBus);
    }

    abstract public void afterStart(AcceptsOneWidget panel, EventBus eventBus);
}
