package hu.gab.wiki.client.front.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperInput;
import hu.gab.wiki.client.mvp.WikiView;

/**
 * @author PG
 * @since 2016-04-30
 */
public class AboutView extends WikiView<AboutPresenter> implements IAboutView {
    @Override
    protected void afterActivitySet() {

    }

    interface AboutViewUiBinder extends UiBinder<Widget, AboutView> {
    }

    @UiField
    PaperButton button;

    @UiField
    PaperInput name;

    private static AboutViewUiBinder ourUiBinder = GWT.create(AboutViewUiBinder.class);

    public AboutView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        addEventHanlders();
    }

    private void addEventHanlders(){
        button.addClickHandler(event -> {
            String name = this.name.getValue();
            if(name.length() > 0){
                activity.addNewUser(name);
            }
        });
    }
}