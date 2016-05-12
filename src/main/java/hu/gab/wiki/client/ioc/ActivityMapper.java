package hu.gab.wiki.client.ioc;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import hu.gab.wiki.client.about.AboutActivity;
import hu.gab.wiki.client.about.AboutPlace;
import hu.gab.wiki.client.events.BeforeActivityChanged;
import hu.gab.wiki.client.home.HomeActivity;
import hu.gab.wiki.client.home.HomePlace;

/**
 * @author PG
 * @since 2016-04-30
 */
public class ActivityMapper implements com.google.gwt.activity.shared.ActivityMapper {
    private ClientFactory clientFactory;

    public ActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        Activity toGo = null;

        if(place instanceof HomePlace){
            toGo = new HomeActivity(clientFactory, clientFactory.getHomeView());
        }
        else if(place instanceof AboutPlace){
            toGo = new AboutActivity(clientFactory, clientFactory.getAboutView());
        }
        else{
            toGo = new HomeActivity(clientFactory, clientFactory.getHomeView());
        }

        clientFactory.getEventBus().fireEvent(new BeforeActivityChanged(place));

        return toGo;
    }
}
