package hu.gab.wiki.client.admin.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import hu.gab.wiki.client.mvp.WikiPlace;
import hu.gab.wiki.shared.RoleEntityName;

import java.util.Arrays;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public class UserAdminPlace extends Place implements WikiPlace {
    @Override
    public List<String> getRequiredRoles() {
        return Arrays.asList(
                RoleEntityName.ADMIN
        );
    }

    @Prefix("UserAdmin")
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
