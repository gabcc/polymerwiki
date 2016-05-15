package hu.gab.wiki.client.admin.user;

import hu.gab.wiki.client.admin.user.widget.UserAdder;
import hu.gab.wiki.client.admin.user.widget.UserModifier;
import hu.gab.wiki.client.mvp.IWikiPresenter;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public interface UserAdminPresenter extends IWikiPresenter<IUserAdminView> {

    void refreshUserList();

    void onUserAdd(UserAdder userAdder);

    void onUserSave(UserModifier modifier);

    void getRoles(NeedsRoleList needsRoleList);

    interface NeedsRoleList{
        void onRolesLoad(List<DTO_Role> roles);
    }
}
