package hu.gab.wiki.client.admin.user;

import hu.gab.wiki.client.mvp.IWikiView;
import hu.gab.wiki.shared.dto.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public interface IUserAdminView extends IWikiView<UserAdminPresenter> {

    void setUserList(List<DTO_User> userList);

    void clearUserList();
}
