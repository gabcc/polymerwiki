package hu.gab.wiki.client.admin.user.widget;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.paper.widget.PaperInput;
import hu.gab.wiki.client.admin.user.UserAdminPresenter;
import hu.gab.wiki.client.widgets.SaveableBootstrapModal;

/**
 * @author PG
 * @since 2016-05-13
 */
public class UserAdder {
    private SaveableBootstrapModal.ModalSaveClickHandler clickHandler;
    private SaveableBootstrapModal modal;

    private UserAdminPresenter userAdminPresenter;

    private PaperInput inputName;
    private PaperInput inputEmail;
    private PaperInput inputPassword;

    public UserAdder(UserAdminPresenter userAdminPresenter) {
        this.userAdminPresenter = userAdminPresenter;

        clickHandler = () -> {
            userAdminPresenter.onUserAdd(this);
        };

        Label label = new Label("Kérlek add meg a kitöltendő elemeket");

        modal = new SaveableBootstrapModal(clickHandler);
        modal.addHeader(label);
        modal.addContent(createModalContent());

    }

    private Widget createModalContent() {
        HTMLPanel container = new HTMLPanel("");

        inputName = new PaperInput();
        inputName.setPlaceholder("Név");

        inputEmail = new PaperInput();
        inputEmail.setPlaceholder("Email");

        inputPassword = new PaperInput();
        inputPassword.setType("password");
        inputPassword.setPlaceholder("Password");

        container.add(inputName);
        container.add(inputEmail);
        container.add(inputPassword);

        return container;
    }

    public PaperInput getInputName() {
        return inputName;
    }

    public PaperInput getInputEmail() {
        return inputEmail;
    }

    public PaperInput getInputPassword() {
        return inputPassword;
    }

    public void show() {
        modal.show();
    }

    public void hide(){
        modal.hide();
    }
}
