package hu.gab.wiki.client.ioc;

import com.google.gwt.place.shared.WithTokenizers;
import hu.gab.wiki.client.about.AboutPlace;
import hu.gab.wiki.client.home.HomePlace;

/**
 * @author PG
 * @since 2016-04-30
 */
@WithTokenizers({
        HomePlace.Tokenizer.class,
        AboutPlace.Tokenizer.class
})
public interface PlaceHistoryMapper extends com.google.gwt.place.shared.PlaceHistoryMapper {
}
