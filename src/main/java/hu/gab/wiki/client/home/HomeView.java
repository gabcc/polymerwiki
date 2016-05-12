package hu.gab.wiki.client.home;

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
public class HomeView extends WikiView<HomePresenter> implements IHomeView {

    interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
    }

    private static HomeViewUiBinder ourUiBinder = GWT.create(HomeViewUiBinder.class);

    @UiField
    PaperButton homeAddButton;
    @UiField
    PaperInput homeAddInput;

    public HomeView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        addGuiEventhandlers();
    }

    private void addGuiEventhandlers(){
    }
}