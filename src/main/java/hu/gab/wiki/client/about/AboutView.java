package hu.gab.wiki.client.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import hu.gab.wiki.client.mvp.WikiView;

/**
 * @author PG
 * @since 2016-04-30
 */
public class AboutView extends WikiView<AboutPresenter> implements IAboutView {
    interface AboutViewUiBinder extends UiBinder<Widget, AboutView> {
    }

    private static AboutViewUiBinder ourUiBinder = GWT.create(AboutViewUiBinder.class);

    public AboutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}