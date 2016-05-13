package hu.gab.wiki.client.drawer;

import com.vaadin.polymer.paper.widget.PaperButton;
import hu.gab.wiki.client.admin.user.UserAdminPlace;
import hu.gab.wiki.client.ioc.ClientFactory;

/**
 * @author PG
 * @since 2016-05-12
 *
 * Teszt jelleggel bizonyos menüpontokat ide töltök be.
 */
public class DrawerMenuFiller {
    private ClientFactory clientFactory;

    public DrawerMenuFiller(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public void fillMenu(){
        PaperButton userAdminButton = new PaperButton("User admin");
        userAdminButton.addClickHandler(event -> {
           clientFactory.getPlaceController().goTo(new UserAdminPlace());
        });


        DrawerManager drawerManager = clientFactory.getDrawerManager();
        drawerManager.addWidget(userAdminButton);
    }
}
