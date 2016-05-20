package hu.gab.wiki.client.admin.user;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.client.admin.user.widget.UserAdder;
import hu.gab.wiki.client.admin.user.widget.UserModifier;
import hu.gab.wiki.client.helper.CommonWikiAsyncHandler;
import hu.gab.wiki.client.helper.UserAdminHelper;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public class UserAdminActivity extends WikiActivity<IUserAdminView> implements UserAdminPresenter {
    public UserAdminActivity(ClientFactory clientFactory, IUserAdminView view) {
        super(clientFactory, view);
    }

    @Override
    public void afterStart(AcceptsOneWidget panel, EventBus eventBus) {
    }

    @Override
    public void refreshUserList() {
        WikiService.App.getInstance().listUsers(AppUtils.getAuthData(), new AsyncCallback<List<DTO_User>>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(List<DTO_User> result) {
                view.setUserList(result);
            }
        });
    }

    @Override
    public void onUserAdd(UserAdder caller) {
        boolean valid = true;

        if (!UserAdminHelper.validateName(caller.getInputName())) {
            valid = false;
        }
        if (!UserAdminHelper.validateEmail(caller.getInputEmail())) {
            valid = false;
        }
        if (!UserAdminHelper.validatePassword(caller.getInputPassword())) {
            valid = false;
        }

        if (valid) {
            AppUtils.showLoadingSpinner();

            String name = caller.getInputName().getValue();
            String email = caller.getInputEmail().getValue();
            String password = caller.getInputPassword().getValue();

            WikiService.App.getInstance().addNewUser(AppUtils.getAuthData(), name, email, password, new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    caller.hide();

                    view.clearUserList();
                    refreshUserList();

                    AppUtils.hideLoadingSpinner();
                    AppUtils.showToast(0, "A felhasználót sikeresen mentettük", false);
                }

                @Override
                public void onFailure(Throwable caught) {
                    AppUtils.hideLoadingSpinner();
                    AppUtils.showToast(0, "Hiba történt a felhasználó mentése közben: " + caught.getMessage(), true);
                }
            });
        }
    }

    @Override
    public void onUserSave(UserModifier modifier) {
        boolean valid = true;

        if (!UserAdminHelper.validateName(modifier.getInputName())) {
            valid = false;
        }

        if (!UserAdminHelper.validateEmail(modifier.getInputEmail())) {
            valid = false;
        }

        if (!modifier.getUserPassword().equals("")) {
            if (!UserAdminHelper.validatePassword(modifier.getInputPassword())) {
                valid = false;
            }
        }

        if (valid) {
            DTO_User modifiedUser = modifier.createModifiedUser();
            AppUtils.showLoadingSpinner();
            WikiService.App.getInstance().updateUser(AppUtils.getAuthData(), modifiedUser, new CommonWikiAsyncHandler<Void>() {
                @Override
                public void onSuccess(Void result) {
                    view.clearUserList();
                    refreshUserList();

                    modifier.hide();
                    AppUtils.hideLoadingSpinner();
                    AppUtils.showToast(0, "Sikeres user update", false);
                }
            });
        }
    }

    @Override
    public void getRoles(NeedsRoleList needsRoleList) {
        AppUtils.showLoadingSpinner();

        WikiService.App.getInstance().getRoles(AppUtils.getAuthData(), new AsyncCallback<List<DTO_Role>>() {
            @Override
            public void onFailure(Throwable caught) {
                AppUtils.hideLoadingSpinner();
                AppUtils.showToast(0, caught.getMessage(), true);
            }

            @Override
            public void onSuccess(List<DTO_Role> result) {
                AppUtils.hideLoadingSpinner();
                needsRoleList.onRolesLoad(result);
            }
        });
    }
}
