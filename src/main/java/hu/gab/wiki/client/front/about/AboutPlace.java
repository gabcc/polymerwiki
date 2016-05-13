package hu.gab.wiki.client.front.about;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author PG
 * @since 2016-04-30
 */
public class AboutPlace extends Place {
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
