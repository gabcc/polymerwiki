package hu.gab.wiki.client.admin.user;

import hu.gab.wiki.client.admin.user.widget.UserAdder;
import hu.gab.wiki.client.mvp.IWikiPresenter;

/**
 * @author PG
 * @since 2016-05-12
 */
public interface UserAdminPresenter extends IWikiPresenter<IUserAdminView> {

    void refreshUserList();

    void onUserAdd(String name, String email, String password, UserAdder userAdder);
}
