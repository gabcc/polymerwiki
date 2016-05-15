package hu.gab.wiki.client.front.login;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperInput;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.client.events.OnLogin;
import hu.gab.wiki.client.helper.CommonWikiAsyncHandler;
import hu.gab.wiki.client.store.ClientStore;
import hu.gab.wiki.client.widgets.BootstrapModal;
import hu.gab.wiki.shared.dto.DTO_LoginData;

/**
 * @author PG
 * @since 2016-05-15
 */
public class LoginModule {
    private BootstrapModal bootstrapModal;

    private PaperInput inputEmail;
    private PaperInput inputPassword;
    private PaperButton loginButton;

    public LoginModule() {
        createModal();
    }

    public void show() {
        bootstrapModal.show();
    }

    public void hide() {
        bootstrapModal.hide();
    }

    private void createModal() {
        Label loginLabel = new Label("Login");

        inputEmail = new PaperInput();
        inputEmail.setPlaceholder("Email");

        inputPassword = new PaperInput();
        inputPassword.setPlaceholder("Password");
        inputPassword.setType("password");

        loginButton = new PaperButton("Do login");
        loginButton.addStyleName("w-btn-secondary");

        loginButton.addClickHandler(event -> {
            onLoginButtonClick();
        });

        HTMLPanel container = new HTMLPanel("");
        container.add(inputEmail);
        container.add(inputPassword);
        container.add(loginButton);

        bootstrapModal = new BootstrapModal(loginLabel, container);
    }

    private void onLoginButtonClick() {
        String email = inputEmail.getValue();
        String password = inputPassword.getValue();

        AppUtils.showLoadingSpinner();

        WikiService.App.getInstance().login(email, password, new CommonWikiAsyncHandler<DTO_LoginData>() {
            @Override
            public void onSuccess(DTO_LoginData result) {
                ClientStore clientStore = AppUtils.getClientFactory().getClientStore();

                clientStore.setToken(result.getToken());
                clientStore.setUser(result.getUser());

                bootstrapModal.hide();
                AppUtils.hideLoadingSpinner();
                AppUtils.showToast(0, "Sikeres bejelentkezés", false);

                AppUtils.getClientFactory().getEventBus().fireEvent(new OnLogin());
            }
        });
    }
}
