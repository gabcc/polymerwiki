package hu.gab.wiki.client.front.article;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import hu.gab.wiki.client.mvp.WikiPlace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticlePlace extends Place implements WikiPlace {
    private static final String ARTICLE_SLUG_PLACE = "slug";

    @Override
    public List<String> getRequiredRoles() {
        return new ArrayList<>();
    }

    private String articleSlug = "";

    public String getArticleSlug() {
        return articleSlug;
    }

    public void setArticleSlug(String articleSlug) {
        this.articleSlug = articleSlug;
    }

    @Prefix("Article")
    public static class Tokenizer implements PlaceTokenizer<ArticlePlace> {
        @Override
        public ArticlePlace getPlace(String token) {

            ArticlePlace articlePlace = new ArticlePlace();

            if (token.indexOf('=') == -1) {
                articlePlace.setArticleSlug("");
            } else {
                String[] split = token.split("=");
                if (split.length < 2) {
                    articlePlace.setArticleSlug("");
                } else {
                    articlePlace.setArticleSlug(split[1]);
                }
            }

            return articlePlace;
        }

        @Override
        public String getToken(ArticlePlace place) {
            if (place.getArticleSlug().equals("")) {
                return "";
            } else {
                return ARTICLE_SLUG_PLACE + "=" + place.getArticleSlug();
            }
        }
    }
}
