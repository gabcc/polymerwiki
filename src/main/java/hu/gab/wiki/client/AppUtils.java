package hu.gab.wiki.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vaadin.polymer.iron.widget.IronIcon;
import com.vaadin.polymer.paper.widget.PaperSpinner;
import com.vaadin.polymer.paper.widget.PaperToast;
import hu.gab.wiki.client.ioc.ClientFactory;

import java.util.logging.Logger;

/**
 * @author PG
 * @since 2016-05-13
 */
public class AppUtils {
    private static final Logger logger = Logger.getLogger(AppUtils.class.getName());

    private static ClientFactory clientFactory = null;

    private static SimplePanel spinnerContainer;
    private static PaperSpinner spinner;

    private static PaperToast toast;
    private static IronIcon toastErrorIcon;

    private static boolean initted = false;

    public static void init(ClientFactory clientFactory) {
        AppUtils.clientFactory = clientFactory;

        spinnerContainer = new SimplePanel();
        spinner = new PaperSpinner("Loading...");

        spinnerContainer.addStyleName("spinner-wrapper-panel");
        spinnerContainer.getElement().getStyle().setDisplay(Style.Display.NONE);

        spinnerContainer.add(spinner);
        spinner.getElement().getStyle().setLeft(50, Style.Unit.PCT);
        spinner.getElement().getStyle().setTop(50, Style.Unit.PCT);

        toast = new PaperToast();
        toastErrorIcon = new IronIcon();
        toastErrorIcon.addStyleName("w-error-icon");

        RootPanel.get().add(spinnerContainer);
        RootPanel.get().add(toast);

        initted = true;
    }

    private static boolean checkInit() {
        if (!initted) {
            logger.severe("Nem volt inicializálva az Apputils");
        }
        return initted;
    }

    public static void showLoadingSpinner() {
        if (checkInit()) {
            spinner.setActive(true);
            spinnerContainer.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        }
    }

    public static void hideLoadingSpinner() {
        if (checkInit()) {
            spinner.setActive(false);
            spinnerContainer.getElement().getStyle().setDisplay(Style.Display.NONE);
        }
    }

    /**
     *
     * @param duration - ha 0, akkor alapon hagyjuk, különben ennyit fog várni
     * @param message
     */
    public static void showToast(int duration, String message, boolean error) {
        if (checkInit()) {
            toast.hide();
            toast.clear();

            if (message == null) {
                logger.warning("Nem volt megadva szöveg a toasthoz");
                return;
            }

            if(duration != 0){
                toast.setDuration(duration);
            }
            toast.setText(message);

            if(error){
                toast.add(toastErrorIcon);
                toastErrorIcon.setIcon("error");
            }

            toast.show();
        }
    }

    public static ClientFactory getClientFactory() {
        return clientFactory;
    }
}
