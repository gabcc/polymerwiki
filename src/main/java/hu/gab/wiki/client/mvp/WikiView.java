package hu.gab.wiki.client.mvp;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;

/**
 * @author PG
 * @since 2016-04-30
 */
abstract public class WikiView<T extends IWikiPresenter> extends Composite implements IWikiView<T> {
    protected T activity;

    @Override
    public void setActivity(T activity) {
        this.activity = activity;
    }

    @Override
    public void makeWidgetSetItself(AcceptsOneWidget acceptsOneWidget) {
        acceptsOneWidget.setWidget(this);
    }
}
