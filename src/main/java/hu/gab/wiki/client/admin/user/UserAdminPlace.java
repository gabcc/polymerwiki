package hu.gab.wiki.client.admin.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author PG
 * @since 2016-05-12
 */
public class UserAdminPlace extends Place {
    public static class Tokenizer implements PlaceTokenizer<UserAdminPlace> {

        @Override
        public UserAdminPlace getPlace(String token) {
            return new UserAdminPlace();
        }

        @Override
        public String getToken(UserAdminPlace place) {
            return "";
        }
    }
}
