package hu.gab.wiki.client.admin.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.vaadin.polymer.paper.widget.PaperButton;
import hu.gab.wiki.client.admin.user.widget.UserAdder;
import hu.gab.wiki.client.mvp.WikiView;
import hu.gab.wiki.client.widgets.SaveableBootstrapModal;
import hu.gab.wiki.shared.dto.DTO_User;

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
    PaperButton buttonDisableSelected;

    private SaveableBootstrapModal newUserModal;

    private CellTable<DTO_User> cellTable = new CellTable<>();

    public UserAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        createCellTable();
        addEventHandlers();
    }

    @Override
    protected void afterActivitySet() {
        activity.refreshUserList();
    }

    @Override
    public void setUserList(List<DTO_User> userList) {
        cellTable.setRowData(0, userList);
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
        });

        cellTable.addColumn(new TextColumn<DTO_User>() {
            @Override
            public String getValue(DTO_User object) {
                if (object != null) {
                    return object.getName();
                }
                return "";
            }
        });

        cellTable.addColumn(new TextColumn<DTO_User>() {
            @Override
            public String getValue(DTO_User object) {
                if (object != null) {
                    return object.getEmail();
                }
                return "";
            }
        });

        celltableHolder.add(cellTable);
    }

    private void addEventHandlers() {
        buttonAddNewUser.addClickHandler(p -> {
            new UserAdder(activity).show();
        });
    }
}