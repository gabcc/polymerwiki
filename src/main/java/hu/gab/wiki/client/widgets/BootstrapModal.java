package hu.gab.wiki.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

/**
 * @author PG
 * @since 2016-05-13
 * <p>
 * Modális wrapper osztály, hogy lehessen bootstrap modalokat használni.
 */
public class BootstrapModal extends Composite {
    interface BootstrapModalUiBinder extends UiBinder<Widget, BootstrapModal> {
    }

    private static BootstrapModalUiBinder ourUiBinder = GWT.create(BootstrapModalUiBinder.class);

    @UiField
    HTMLPanel modal;

    @UiField
    SimplePanel modalHeader;

    @UiField
    SimplePanel modalContent;

    private Widget content;

    public BootstrapModal(Widget header, Widget content) {
        doWidgetInit();

        modalHeader.add(header);
        modalContent.add(content);
    }

    public BootstrapModal() {
        doWidgetInit();
    }

    protected void doWidgetInit(){
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void addHeader(Widget header) {
        modalHeader.clear();
        modalHeader.add(header);
    }

    public void addContent(Widget content) {
        modalContent.clear();
        modalContent.add(content);
    }

    public void show() {
        RootPanel.get().add(modal);
        show(modal.getElement());
    }

    protected native void show(Element element)/*-{
        var $ = $wnd.jQuery;

        console.log($(element));

        $(element).modal('show');
    }-*/;

    public void hide() {
        modal.removeFromParent();
        hide(modal.getElement());
    }

    protected native void hide(Element element)/*-{
        var $ = $wnd.jQuery;
        $(element).modal('hide');
    }-*/;
}