package hu.gab.wiki.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import hu.gab.wiki.client.ioc.ClientFactory;

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
}
