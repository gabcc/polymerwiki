package hu.gab.wiki.client.drawer;

import com.google.gwt.user.client.ui.Label;
import com.vaadin.polymer.paper.widget.PaperButton;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.admin.article.ArticleAdminPlace;
import hu.gab.wiki.client.admin.user.UserAdminPlace;
import hu.gab.wiki.client.front.article.ArticlePlace;
import hu.gab.wiki.client.ioc.ClientFactory;

/**
 * @author PG
 * @since 2016-05-12
 *
 * Teszt jelleggel bizonyos menüpontokat ide töltök be.
 */
public class DrawerMenuFiller {

    private static final String ADMIN_LABEL_ID = "drawer-admin-label";

    public DrawerMenuFiller() {
    }

    public void fillAdminMenu(){
        ClientFactory clientFactory = AppUtils.getClientFactory();

        PaperButton userAdminButton = new PaperButton("User admin");
        userAdminButton.addClickHandler(event -> {
           clientFactory.getPlaceController().goTo(new UserAdminPlace());
        });

        PaperButton articleAdminButton = new PaperButton("Article admin");
        articleAdminButton.addClickHandler(event -> {
           clientFactory.getPlaceController().goTo(new ArticleAdminPlace());
        });

        Label adminLabel = new Label("Admin");
        adminLabel.addStyleName("drawer-header-label");

        DrawerManager drawerManager = clientFactory.getDrawerManager();
        drawerManager.addWidget(adminLabel, DrawerMenuType.ADMIN);
        drawerManager.addWidget(userAdminButton, DrawerMenuType.ADMIN);
        drawerManager.addWidget(articleAdminButton, DrawerMenuType.ADMIN);
    }

    public void fillVisitorMenu(){
        ClientFactory clientFactory = AppUtils.getClientFactory();

        PaperButton visitorButton1 = new PaperButton("Teszt visitoros gomb1");
        PaperButton visitorButton2 = new PaperButton("Teszt visitoros gomb2");

        Label visitorLabel = new Label("Visitor");
        visitorLabel.addStyleName("drawer-header-label");

        DrawerManager drawerManager = clientFactory.getDrawerManager();
        drawerManager.addWidget(visitorLabel, DrawerMenuType.VISITOR);
        drawerManager.addWidget(visitorButton1, DrawerMenuType.VISITOR);
        drawerManager.addWidget(visitorButton2, DrawerMenuType.VISITOR);
    }
}
