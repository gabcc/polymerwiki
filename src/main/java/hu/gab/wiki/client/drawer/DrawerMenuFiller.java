package hu.gab.wiki.client.drawer;

import com.vaadin.polymer.paper.widget.PaperButton;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.admin.user.UserAdminPlace;
import hu.gab.wiki.client.ioc.ClientFactory;

/**
 * @author PG
 * @since 2016-05-12
 *
 * Teszt jelleggel bizonyos menüpontokat ide töltök be.
 */
public class DrawerMenuFiller {
    public DrawerMenuFiller() {
    }

    public void fillMenu(){
        ClientFactory clientFactory = AppUtils.getClientFactory();

        PaperButton userAdminButton = new PaperButton("User admin");
        userAdminButton.addClickHandler(event -> {
           clientFactory.getPlaceController().goTo(new UserAdminPlace());
        });


        DrawerManager drawerManager = clientFactory.getDrawerManager();
        drawerManager.addWidget(userAdminButton);
    }
}
