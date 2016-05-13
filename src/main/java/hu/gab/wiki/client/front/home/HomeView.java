package hu.gab.wiki.client.front.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import hu.gab.wiki.client.mvp.WikiView;

/**
 * @author PG
 * @since 2016-04-30
 */
public class HomeView extends WikiView<HomePresenter> implements IHomeView {

    @Override
    protected void afterActivitySet() {

    }

    interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
    }

    private static HomeViewUiBinder ourUiBinder = GWT.create(HomeViewUiBinder.class);


    public HomeView() {
        initWidget(ourUiBinder.createAndBindUi(this));
        addGuiEventhandlers();
    }

    private void addGuiEventhandlers() {
    }
}