package hu.gab.wiki.client.mvp;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author PG
 * @since 2016-04-30
 */
public interface IWikiView<T extends IWikiPresenter> {
    void setActivity(T activity);

    void makeWidgetSetItself(AcceptsOneWidget acceptsOneWidget);
}
