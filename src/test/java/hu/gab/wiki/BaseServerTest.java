package hu.gab.wiki;

import hu.gab.wiki.server.ioc.Container;

/**
 * @author PG
 * @since 2016-05-20
 */
public class BaseServerTest {
    protected Container container;

    public BaseServerTest() {
        container = new Container();
        container.init();
    }
}
