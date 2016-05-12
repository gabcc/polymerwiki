package hu.gab.wiki.client.wrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
import com.vaadin.polymer.paper.widget.PaperIconButton;
import hu.gab.wiki.client.about.AboutPlace;
import hu.gab.wiki.client.events.BeforeActivityChanged;
import hu.gab.wiki.client.home.HomePlace;
import hu.gab.wiki.client.ioc.ClientFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
public class MainWrapper extends Composite {
    interface MainWrapperUiBinder extends UiBinder<Widget, MainWrapper> {
    }

    @UiField
    HTMLPanel wrapper;

    @UiField
    HTMLPanel menu;

    @UiField
    SimplePanel contentContainer;

    @UiField
    PaperButton buttonHome;
    @UiField
    PaperButton buttonAbout;


    @UiField
    PaperIconButton buttonDrawer;
    @UiField
    HTMLPanel drawerContainer;

    @UiField
    PaperDrawerPanel drawerPanel;

    List<Widget> menuButtons = new ArrayList<>();

    private ClientFactory clientFactory;

    private static MainWrapperUiBinder ourUiBinder = GWT.create(MainWrapperUiBinder.class);

    public MainWrapper(ClientFactory clientFactory) {
        initWidget(ourUiBinder.createAndBindUi(this));

        this.clientFactory = clientFactory;

        initMenuButtons();

        addEventHandlers();
        addGuiEventHandlers();
    }

    public SimplePanel getContentContainer() {
        return contentContainer;
    }

    public HTMLPanel getDrawerContainer() {
        return drawerContainer;
    }

    private void initMenuButtons() {
        menuButtons = Arrays.asList(buttonHome, buttonAbout);
    }

    private void addEventHandlers() {
        EventBus eventBus = clientFactory.getEventBus();

        eventBus.addHandler(BeforeActivityChanged.TYPE, new BeforeActivityChanged.BeforeActivityChangedEventHandler() {
            @Override
            public void onMenuChanged(BeforeActivityChanged event) {
                Place newPlace = event.getNewPlace();

                if (newPlace instanceof HomePlace) {
                    setMenuActive(buttonHome);
                } else if (newPlace instanceof AboutPlace) {
                    setMenuActive(buttonAbout);
                } else {
                    GWT.log("valami nem oké, olyan place-t kaptunk, amit nem tud kezelni a menü..");
                }
            }
        });
    }

    private void setMenuActive(PaperButton paperButton) {
        for (Widget widget : menuButtons) {
            widget.removeStyleName("active");
        }

        paperButton.addStyleName("active");
    }

    private void addGuiEventHandlers() {
        PlaceController placeController = clientFactory.getPlaceController();

        buttonHome.addClickHandler(event -> {
            placeController.goTo(new HomePlace());
        });

        buttonAbout.addClickHandler(event -> {
            placeController.goTo(new AboutPlace());
        });

        buttonDrawer.addClickHandler(event -> {
            drawerPanel.togglePanel();
        });
    }
}