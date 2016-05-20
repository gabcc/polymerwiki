package hu.gab.wiki.client.admin.article;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import hu.gab.wiki.client.mvp.WikiPlace;
import hu.gab.wiki.shared.RoleEntityName;

import java.util.Arrays;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticleAdminPlace extends Place implements WikiPlace {
    @Override
    public List<String> getRequiredRoles() {
        return Arrays.asList(
                RoleEntityName.ADMIN
        );
    }

    @Prefix("ArticleAdmin")
    public static class Tokenizer implements PlaceTokenizer<ArticleAdminPlace> {

        @Override
        public ArticleAdminPlace getPlace(String token) {
            return new ArticleAdminPlace();
        }

        @Override
        public String getToken(ArticleAdminPlace place) {
            return "";
        }
    }
}
