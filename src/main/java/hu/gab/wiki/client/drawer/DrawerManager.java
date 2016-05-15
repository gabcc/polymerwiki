package hu.gab.wiki.client.drawer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.events.OnLogin;
import hu.gab.wiki.client.helper.AuthHelper;
import hu.gab.wiki.shared.FrontRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-09
 * <p>
 * A bal oldali drawert manageli. Ezen az osztályon kereszül lehet majd elérni és beletenni menüpontokat.
 */
public class DrawerManager {
    List<Widget> widgets = new ArrayList<>();
    HTMLPanel drawerPanel = null;

    HTMLPanel simpleMenusContainer = null;
    HTMLPanel adminMenusContainer = null;

    public void addWidget(Widget widget, DrawerMenuType menuType) {
        if (widget == null) throw new RuntimeException("Null parameter: widget");

        widgets.add(widget);

        if(menuType.equals(DrawerMenuType.ADMIN)){
            adminMenusContainer.add(widget);
        }
        else if(menuType.equals(DrawerMenuType.VISITOR)){
            simpleMenusContainer.add(widget);
        }
    }

    public DrawerManager(HTMLPanel drawerPanel) {
        if (drawerPanel == null) throw new RuntimeException("Null parameter: drawerPanel");

        this.drawerPanel = drawerPanel;

        simpleMenusContainer = new HTMLPanel("");
        adminMenusContainer = new HTMLPanel("");

        simpleMenusContainer.addStyleName("layout vertical");

        adminMenusContainer.addStyleName("layout vertical");
        adminMenusContainer.setVisible(false);

        drawerPanel.add(simpleMenusContainer);
        drawerPanel.add(adminMenusContainer);
    }

    public void initEventHandlers(){
        addEventHandlers();
    }

    private void addEventHandlers(){
        AppUtils.getClientFactory().getEventBus().addHandler(OnLogin.TYPE, new OnLogin.OnLoginHandler() {
            @Override
            public void onOnLogin(OnLogin event) {
                adminMenusContainer.clear();
                new DrawerMenuFiller().fillAdminMenu();

                if(AuthHelper.instance.isEligibleForAction(Arrays.asList(FrontRole.ADMIN))){
                    adminMenusContainer.setVisible(true);
                }
            }
        });
    }
}
