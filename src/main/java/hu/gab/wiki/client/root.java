package hu.gab.wiki.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.vaadin.polymer.Polymer;
import hu.gab.wiki.client.drawer.DrawerManager;
import hu.gab.wiki.client.drawer.DrawerMenuFiller;
import hu.gab.wiki.client.front.home.HomePlace;
import hu.gab.wiki.client.ioc.ActivityMapper;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.ioc.ClientFactoryImpl;
import hu.gab.wiki.client.ioc.PlaceHistoryMapper;
import hu.gab.wiki.client.wrapper.MainWrapper;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class root implements EntryPoint {

    private static final Place START_PLACE = new HomePlace();

    private ClientFactory clientFactory;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        clientFactory = GWT.create(ClientFactory.class);
        loadPolymerDependencies();

        SimplePanel webAppContaier = new SimplePanel();
        MainWrapper mainWrapper = new MainWrapper(clientFactory);

        DrawerManager drawerManager = new DrawerManager(mainWrapper.getDrawerContainer());
        ((ClientFactoryImpl)clientFactory).setDrawerManager(drawerManager);

        webAppContaier.add(mainWrapper);
        RootPanel.get().add(webAppContaier);

        initIOC_Container(mainWrapper.getContentContainer(), START_PLACE);

        afterLoad();
    }

    private void initIOC_Container(SimplePanel simplePanel, Place defaultPlace) {
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

        ActivityMapper activityMapper = new ActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(simplePanel);

        PlaceHistoryMapper placeHistoryMapper = GWT.create(PlaceHistoryMapper.class);
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);
        placeHistoryHandler.register(placeController, eventBus, defaultPlace);

        placeHistoryHandler.handleCurrentHistory();
    }

    private void loadPolymerDependencies(){
        Polymer.importHref("iron-icons/iron-icons.html");
        Polymer.importHref("iron-flex-layout/iron-flex-layout.html");

        Polymer.importHref("paper-button/paper-button.html");
        Polymer.importHref("paper-input/paper-input.html");
        Polymer.importHref("paper-drawer-panel/paper-drawer-panel.html");
        Polymer.importHref("paper-header-panel/paper-header-panel.html");
        Polymer.importHref("paper-toolbar/paper-toolbar.html");
        Polymer.importHref("paper-icon-button/paper-icon-button.html");
    }

    private void afterLoad(){
        new DrawerMenuFiller(clientFactory).fillMenu();
    }
}
