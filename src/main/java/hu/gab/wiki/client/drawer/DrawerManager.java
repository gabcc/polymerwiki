package hu.gab.wiki.client.drawer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
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

    public void addWidget(Widget widget) {
        if (widget == null) throw new RuntimeException("Null parameter: widget");

        widgets.add(widget);
        drawerPanel.add(widget);
    }

    public DrawerManager(HTMLPanel drawerPanel) {
        if (drawerPanel == null) throw new RuntimeException("Null parameter: drawerPanel");

        this.drawerPanel = drawerPanel;
    }
}
