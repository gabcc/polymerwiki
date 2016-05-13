package hu.gab.wiki.client.front.about;

import hu.gab.wiki.client.mvp.IWikiPresenter;

/**
 * @author PG
 * @since 2016-04-30
 */
public interface AboutPresenter extends IWikiPresenter<IAboutView> {
    void addNewUser(String name);
}
