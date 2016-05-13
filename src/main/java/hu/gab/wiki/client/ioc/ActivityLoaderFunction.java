package hu.gab.wiki.client.ioc;

import com.google.gwt.activity.shared.Activity;

/**
 * @author PG
 * @since 2016-05-12
 */
@FunctionalInterface
public interface ActivityLoaderFunction {
    Activity createActivity(ClientFactory clientFactory);
}
