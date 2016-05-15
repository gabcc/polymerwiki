package hu.gab.wiki.client.admin.user.widget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.polymer.paper.widget.PaperInput;
import com.vaadin.polymer.paper.widget.PaperRadioButton;
import com.vaadin.polymer.paper.widget.PaperRadioGroup;
import hu.gab.wiki.client.admin.user.UserAdminPresenter;
import hu.gab.wiki.client.widgets.SaveableBootstrapModal;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PG
 * @since 2016-05-15
 */
public class UserModifier implements UserAdminPresenter.NeedsRoleList, SaveableBootstrapModal.ModalSaveClickHandler {

    private DTO_User user;

    private UserAdminPresenter presenter;

    private SaveableBootstrapModal modal;

    private PaperInput inputName;
    private PaperInput inputEmail;
    private PaperInput inputPassword;

    private PaperRadioGroup radioGroup;
    private Map<DTO_Role, PaperRadioButton> rolePaperRadioButtonMap = new HashMap<>();

    public UserModifier(DTO_User user, UserAdminPresenter presenter) {
        this.user = user;
        this.presenter = presenter;

        createModal();
        fetchRoles();
    }

    public void show() {
        modal.show();
    }

    public void hide() {
        modal.hide();
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

    public String getUserName() {
        return inputName.getValue();
    }

    public String getUserEmail() {
        return inputEmail.getValue();
    }

    public String getUserPassword() {
        return inputPassword.getValue();
    }

    public DTO_User createModifiedUser(){
        DTO_User dto_user = new DTO_User();
        dto_user.setId(user.getId());

        dto_user.setName(getUserName());
        dto_user.setEmail(getUserEmail());

        String password = getUserPassword();
        if(!password.equals("")){
            dto_user.setPassword(password);
        }

        dto_user.setRoles(getUserRoles());

        return dto_user;
    }

    public List<DTO_Role> getUserRoles() {
        List<DTO_Role> activeRoles = new ArrayList<>();

        for (Map.Entry<DTO_Role, PaperRadioButton> entry : rolePaperRadioButtonMap.entrySet()) {
            if(entry.getValue().getActive()){
                activeRoles.add(entry.getKey());
            }
        }

        return activeRoles;
    }

    private void createModal() {
        Label header = new Label("Felhasználó adatainak módosítása");

        HTMLPanel inputContainer = new HTMLPanel("");

        inputName = new PaperInput();
        inputName.setValue(user.getName());
        inputName.setPlaceholder("Név");

        inputEmail = new PaperInput();
        inputEmail.setValue(user.getEmail());
        inputEmail.setPlaceholder("Email");

        inputPassword = new PaperInput();
        inputPassword.setValue("");
        inputPassword.setPlaceholder("password");

        Label roleLabel = new Label("Hozzárendelt szerepek:");
        radioGroup = new PaperRadioGroup();

        inputContainer.add(inputName);
        inputContainer.add(inputEmail);
        inputContainer.add(inputPassword);

        inputContainer.add(roleLabel);
        inputContainer.add(radioGroup);

        modal = new SaveableBootstrapModal(header, inputContainer, this);
    }

    private void fetchRoles() {
        presenter.getRoles(this);
    }

    @Override
    public void onRolesLoad(List<DTO_Role> roles) {
        for (DTO_Role role : roles) {
            PaperRadioButton roleRadioButton = new PaperRadioButton(role.getName());
            rolePaperRadioButtonMap.put(role, roleRadioButton);

            radioGroup.add(roleRadioButton);
        }

        for (DTO_Role role : user.getRoles()) {
            if (roles.contains(role)) {
                rolePaperRadioButtonMap.get(role).setActive(true);
            }
        }
    }

    @Override
    public void onSaveClick() {
        presenter.onUserSave(this);
    }
}
