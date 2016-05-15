package hu.gab.wiki.client.front.about;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import hu.gab.wiki.client.mvp.WikiPlace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-04-30
 */
public class AboutPlace extends Place implements WikiPlace {
    @Override
    public List<String> getRequiredRoles() {
        return new ArrayList<>();
    }

    @Prefix("About")
    public static class Tokenizer implements PlaceTokenizer<AboutPlace> {
        @Override
        public AboutPlace getPlace(String token) {
            return new AboutPlace();
        }

        @Override
        public String getToken(AboutPlace place) {
            return "";
        }
    }
}
