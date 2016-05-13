package hu.gab.wiki.client.ioc;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import hu.gab.wiki.client.admin.user.UserAdminActivity;
import hu.gab.wiki.client.admin.user.UserAdminPlace;
import hu.gab.wiki.client.events.BeforeActivityChanged;
import hu.gab.wiki.client.front.about.AboutActivity;
import hu.gab.wiki.client.front.about.AboutPlace;
import hu.gab.wiki.client.front.home.HomeActivity;
import hu.gab.wiki.client.front.home.HomePlace;
import hu.gab.wiki.shared.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
public class ActivityMapper implements com.google.gwt.activity.shared.ActivityMapper {
    private ClientFactory clientFactory;

    private List<Tuple<Class, ActivityLoaderFunction>> placeActivityCreators = new ArrayList<>();

    public ActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

        initActivityMap();
    }

    private void initActivityMap() {
        placeActivityCreators.add(new Tuple<>(HomePlace.class, (c) -> new HomeActivity(c, c.getHomeView())));
        placeActivityCreators.add(new Tuple<>(AboutPlace.class, (c) -> new AboutActivity(c, c.getAboutView())));

        placeActivityCreators.add(new Tuple<>(UserAdminPlace.class, (c) -> new UserAdminActivity(c, c.getUserAdminView())));
    }

    @Override
    public Activity getActivity(Place place) {
        Activity toGo = new HomeActivity(clientFactory, clientFactory.getHomeView());

        for(Tuple<Class, ActivityLoaderFunction> functionTuple : placeActivityCreators){
            if(functionTuple.getT1().equals(place.getClass())){
                toGo = functionTuple.getT2().createActivity(clientFactory);
            }
        }

        clientFactory.getEventBus().fireEvent(new BeforeActivityChanged(place));

        return toGo;
    }
}
