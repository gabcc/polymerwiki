package hu.gab.wiki.client.ioc;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.client.admin.article.ArticleAdminActivity;
import hu.gab.wiki.client.admin.article.ArticleAdminPlace;
import hu.gab.wiki.client.admin.user.UserAdminActivity;
import hu.gab.wiki.client.admin.user.UserAdminPlace;
import hu.gab.wiki.client.events.BeforeActivityChanged;
import hu.gab.wiki.client.front.about.AboutActivity;
import hu.gab.wiki.client.front.about.AboutPlace;
import hu.gab.wiki.client.front.article.ArticleActivity;
import hu.gab.wiki.client.front.article.ArticlePlace;
import hu.gab.wiki.client.front.home.HomeActivity;
import hu.gab.wiki.client.front.home.HomePlace;
import hu.gab.wiki.client.mvp.WikiPlace;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
public class ActivityMapper implements com.google.gwt.activity.shared.ActivityMapper {
    private ClientFactory clientFactory;

    private static Place DEFAULT_PLACE = new HomePlace();

    public ActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        Activity toGo = new HomeActivity(clientFactory, clientFactory.getHomeView());

        if (place instanceof WikiPlace) {
            if (!isAuthorized((WikiPlace) place)) {
                return getActivity(DEFAULT_PLACE);
            }
        }

        if (place instanceof HomePlace) {
            return new HomeActivity(clientFactory, clientFactory.getHomeView());
        }
        if (place instanceof AboutPlace) {
            return new AboutActivity(clientFactory, clientFactory.getAboutView());
        }
        if (place instanceof ArticlePlace) {
            return new ArticleActivity(clientFactory, clientFactory.getArticleView());
        }
        if (place instanceof UserAdminPlace) {
            return new UserAdminActivity(clientFactory, clientFactory.getUserAdminView());
        }
        if (place instanceof ArticleAdminPlace) {
            return new ArticleAdminActivity(clientFactory, clientFactory.getArticleAdminView());
        }

        clientFactory.getEventBus().fireEvent(new BeforeActivityChanged(place));

        return toGo;
    }

    private boolean isAuthorized(WikiPlace wikiPlace) {
        boolean authorized = true;

        List<String> roles = new ArrayList<>();

        DTO_User user = AppUtils.getClientFactory().getClientStore().getUser();
        if (user != null) {
            for (DTO_Role role : user.getRoles()) {
                roles.add(role.getName());
            }
        }

        List<String> requiredRoles = wikiPlace.getRequiredRoles();
        for (String role : requiredRoles) {
            if (!roles.contains(role)) {
                authorized = false;
            }
        }

        return authorized;
    }
}
