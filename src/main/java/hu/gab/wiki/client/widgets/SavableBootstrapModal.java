package hu.gab.wiki.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.paper.widget.PaperButton;

/**
 * @author PG
 * @since 2016-05-13
 */
public class SavableBootstrapModal extends BootstrapModal {
    interface SaveableBootstrapModelUiBinder extends UiBinder<Widget, SavableBootstrapModal> {
    }

    private static SaveableBootstrapModelUiBinder ourUiBinder = GWT.create(SaveableBootstrapModelUiBinder.class);

    @UiField
    PaperButton buttonSave;

    @UiField
    PaperButton buttonClose;

    private ModalSaveClickHandler clickHandler;


    public SavableBootstrapModal(ModalSaveClickHandler saveClickHandler) {
        super();

        this.clickHandler = saveClickHandler;
        addEventHandlers();
    }

    public SavableBootstrapModal(Widget header, Widget content, ModalSaveClickHandler saveClickHandler) {
        super(header, content);

        this.clickHandler = saveClickHandler;
        addEventHandlers();
    }

    @Override
    protected void doWidgetInit() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    private void addEventHandlers() {
        buttonClose.addClickHandler(p -> {
            hide();
        });

        buttonSave.addClickHandler(p -> {
            clickHandler.onSaveClick();
        });
    }

    @FunctionalInterface
    public interface ModalSaveClickHandler {
        void onSaveClick();
    }
}