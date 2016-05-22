package hu.gab.wiki.client.admin.user;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.vaadin.polymer.paper.widget.PaperButton;
import hu.gab.wiki.client.admin.user.widget.UserAdder;
import hu.gab.wiki.client.admin.user.widget.UserModifier;
import hu.gab.wiki.client.mvp.WikiView;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.status.UserStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public class UserAdminView extends WikiView<UserAdminPresenter> implements IUserAdminView {
    interface UserAdminUiBinder extends UiBinder<Widget, UserAdminView> {
    }

    private static UserAdminUiBinder ourUiBinder = GWT.create(UserAdminUiBinder.class);

    @UiField
    SimplePanel celltableHolder;

    @UiField
    PaperButton buttonAddNewUser;

    @UiField
    PaperButton buttonSaveChanges;

    private CellTable<DTO_User> cellTable = new CellTable<>();

    public UserAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        createCellTable();
    }

    @Override
    protected void afterActivitySet() {
        activity.refreshUserList();
    }

    @Override
    public void setUserList(List<DTO_User> userList) {
        cellTable.setRowData(0, new ArrayList<>());
        cellTable.setRowData(0, userList);
        cellTable.redraw();
    }

    @Override
    public void clearUserList() {
        cellTable.setRowData(0, new ArrayList<>());
    }


    private void createCellTable() {
        cellTable.addStyleName("table table-hover");
        cellTable.setPageSize(10);
        cellTable.setPageStart(0);

        AsyncDataProvider<DTO_User> asyncDataProvider = new AsyncDataProvider<DTO_User>() {
            @Override
            protected void onRangeChanged(HasData<DTO_User> display) {
            }
        };

        asyncDataProvider.addDataDisplay(cellTable);

        cellTable.addColumn(new TextColumn<DTO_User>() {
            @Override
            public String getValue(DTO_User object) {
                if (object != null) {
                    return String.valueOf(object.getId());
                }
                return "";
            }
        }, "Id");

        cellTable.addColumn(new TextColumn<DTO_User>() {
            @Override
            public String getValue(DTO_User object) {
                if (object != null) {
                    return object.getName();
                }
                return "";
            }
        }, "Name");

        cellTable.addColumn(new TextColumn<DTO_User>() {
            @Override
            public String getValue(DTO_User object) {
                if (object != null) {
                    return object.getEmail();
                }
                return "";
            }
        }, "Email");

        CheckboxCell checkboxCell = new CheckboxCell();
        Column<DTO_User, Boolean> statusColumn = new Column<DTO_User, Boolean>(checkboxCell) {
            @Override
            public Boolean getValue(DTO_User object) {
                if (object.getStatus().equals(UserStatus.ACTIVE)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        statusColumn.setFieldUpdater(new FieldUpdater<DTO_User, Boolean>() {
            @Override
            public void update(int index, DTO_User object, Boolean value) {
                object.setStatus(value ? UserStatus.ACTIVE : UserStatus.DISABLED);
                onUserStatusChanged(object);

                activity.onUserStatusChanged(object);
            }
        });

        cellTable.addColumn(statusColumn, "Status");


        Column<DTO_User, String> detailsButtonCol = new Column<DTO_User, String>(new ButtonCell()) {
            @Override
            public String getValue(DTO_User object) {
                return "Részletek";
            }
        };
        detailsButtonCol.setFieldUpdater((index, object, value) -> {
            onUserDetailsClick(object);
        });
        cellTable.addColumn(detailsButtonCol, "Részletek");

        cellTable.setRowStyles(new RowStyles<DTO_User>() {
            @Override
            public String getStyleNames(DTO_User row, int rowIndex) {
                if (row.getStatus().equals(UserStatus.DISABLED)) {
                    return "w-tablerow-disabled";
                } else {
                    return "";
                }
            }
        });

        celltableHolder.add(cellTable);
    }

    private void onUserDetailsClick(DTO_User user) {
        UserModifier userModifier = new UserModifier(user, activity);
        userModifier.show();
    }

    private void onUserStatusChanged(DTO_User user) {
        activity.onUserStatusChanged(user);
    }


    @UiHandler("buttonAddNewUser")
    public void onAddNewUserButtonClick(ClickEvent event){
        new UserAdder(activity).show();
    }

    @UiHandler("buttonSaveChanges")
    public void onUserChangesSaveClick(ClickEvent event){
        activity.saveUserChanges();
    }
}