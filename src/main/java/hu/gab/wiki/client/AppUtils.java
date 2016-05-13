package hu.gab.wiki.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vaadin.polymer.paper.widget.PaperSpinner;

/**
 * @author PG
 * @since 2016-05-13
 */
public class AppUtils {
    private static SimplePanel spinnerContainer;

    private static PaperSpinner spinner;

    static {
        spinnerContainer = new SimplePanel();
        spinner = new PaperSpinner("Loading...");

        spinnerContainer.addStyleName("spinner-wrapper-panel");
        spinnerContainer.getElement().getStyle().setDisplay(Style.Display.NONE);

        spinnerContainer.add(spinner);
        spinner.getElement().getStyle().setLeft(50, Style.Unit.PCT);
        spinner.getElement().getStyle().setTop(50, Style.Unit.PCT);

        RootPanel.get().add(spinnerContainer);
    }

    public static void showLoadingSpinner() {
        spinner.setActive(true);
        spinnerContainer.getElement().getStyle().setDisplay(Style.Display.BLOCK);
    }

    public static void hideLoadingSpinner() {
        spinner.setActive(false);
        spinnerContainer.getElement().getStyle().setDisplay(Style.Display.NONE);
    }
}
