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
import hu.gab.wiki.client.events.OnLogin;
import hu.gab.wiki.client.front.home.HomePlace;
import hu.gab.wiki.client.helper.CommonWikiAsyncHandler;
import hu.gab.wiki.client.ioc.ActivityMapper;
import hu.gab.wiki.client.ioc.ClientFactory;
import hu.gab.wiki.client.ioc.ClientFactoryImpl;
import hu.gab.wiki.client.ioc.PlaceHistoryMapper;
import hu.gab.wiki.client.store.ClientStore;
import hu.gab.wiki.client.wrapper.MainWrapper;
import hu.gab.wiki.shared.dto.DTO_LoginData;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class root implements EntryPoint {

    private static final Place START_PLACE = new HomePlace();

    private ClientFactory clientFactory;
    private DrawerManager drawerManager;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        clientFactory = GWT.create(ClientFactory.class);
        loadPolymerDependencies();
        AppUtils.setClientFactory(clientFactory);

        SimplePanel webAppContaier = new SimplePanel();
        MainWrapper mainWrapper = new MainWrapper(clientFactory);

        drawerManager = new DrawerManager(mainWrapper.getDrawerContainer());
        ((ClientFactoryImpl) clientFactory).setDrawerManager(drawerManager);

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

    private void loadPolymerDependencies() {
        Polymer.importHref("iron-icons/iron-icons.html");
        Polymer.importHref("iron-flex-layout/iron-flex-layout.html");

        Polymer.importHref("paper-button/paper-button.html");
        Polymer.importHref("paper-input/paper-input.html");
        Polymer.importHref("paper-drawer-panel/paper-drawer-panel.html");
        Polymer.importHref("paper-header-panel/paper-header-panel.html");
        Polymer.importHref("paper-toolbar/paper-toolbar.html");
        Polymer.importHref("paper-icon-button/paper-icon-button.html");
        Polymer.importHref("paper-toast/paper-toast.html");
    }

    private void afterLoad() {
        AppUtils.init();
        drawerManager.initEventHandlers();

        new DrawerMenuFiller().fillVisitorMenu();

        AppUtils.showLoadingSpinner();
        WikiService.App.getInstance().login("g.percze@gmail.com", "123456", new CommonWikiAsyncHandler<DTO_LoginData>() {
            @Override
            public void onSuccess(DTO_LoginData result) {
                AppUtils.hideLoadingSpinner();

                ClientStore clientStore = AppUtils.getClientFactory().getClientStore();
                clientStore.setUser(result.getUser());
                clientStore.setToken(result.getToken());

                AppUtils.getClientFactory().getEventBus().fireEvent(new OnLogin());
            }
        });
    }
}
