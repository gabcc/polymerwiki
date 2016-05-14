package hu.gab.wiki.client.admin.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.client.admin.user.widget.UserAdder;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.mvp.WikiActivity;
import hu.gab.wiki.shared.dto.DTO_User;

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
        WikiService.App.getInstance().listUsers(new AsyncCallback<List<DTO_User>>() {
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
    public void onUserAdd(String name, String email, String password, UserAdder caller) {
        boolean error = false;

        if (name == null || name.length() == 0) {
            caller.getInputName().setErrorMessage("name is missing");
            caller.getInputName().setInvalid(true);
            error = true;
        } else {
            caller.getInputName().setInvalid(false);
        }

        if (email == null || email.length() == 0) {
            caller.getInputEmail().setErrorMessage("Email is not valid");
            caller.getInputEmail().setInvalid(true);
            error = true;
        } else {
            caller.getInputEmail().setInvalid(false);
        }

        if (password == null || password.length() == 0) {
            caller.getInputPassword().setErrorMessage("password is missing");
            caller.getInputPassword().setInvalid(true);
            error = true;
        } else {
            caller.getInputPassword().setInvalid(false);
        }

        if (error == false) {
            AppUtils.showLoadingSpinner();

            WikiService.App.getInstance().addNewUser(name, email, password, new AsyncCallback<Void>() {
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


}
