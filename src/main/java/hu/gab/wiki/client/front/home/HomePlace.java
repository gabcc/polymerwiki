package hu.gab.wiki.client.front.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.place.shared.WithTokenizers;
import hu.gab.wiki.client.mvp.WikiPlace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
public class HomePlace extends Place implements WikiPlace{
    @Override
    public List<String> getRequiredRoles() {
        return new ArrayList<>();
    }

    @Prefix("Home")
    public static class Tokenizer implements PlaceTokenizer<HomePlace> {

        @Override
        public HomePlace getPlace(String token) {
            return new HomePlace();
        }

        @Override
        public String getToken(HomePlace place) {
            return "";
        }
    }
}
