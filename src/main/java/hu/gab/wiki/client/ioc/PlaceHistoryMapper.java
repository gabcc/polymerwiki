package hu.gab.wiki.client.ioc;

import com.google.gwt.place.shared.WithTokenizers;
import hu.gab.wiki.client.admin.article.ArticleAdminPlace;
import hu.gab.wiki.client.admin.user.UserAdminPlace;
import hu.gab.wiki.client.front.about.AboutPlace;
import hu.gab.wiki.client.front.article.ArticlePlace;
import hu.gab.wiki.client.front.home.HomePlace;

/**
 * @author PG
 * @since 2016-04-30
 */
@WithTokenizers({
        HomePlace.Tokenizer.class,
        AboutPlace.Tokenizer.class,
        ArticlePlace.Tokenizer.class,

        UserAdminPlace.Tokenizer.class,
        ArticleAdminPlace.Tokenizer.class
})
public interface PlaceHistoryMapper extends com.google.gwt.place.shared.PlaceHistoryMapper {
}
