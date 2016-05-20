package hu.gab.wiki.server.ioc;

import com.google.inject.AbstractModule;
import hu.gab.wiki.server.service.*;

/**
 * @author PG
 * @since 2016-05-20
 *
 */
public class WikiModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(AuthService.class);
        bind(CategotyService.class);
        bind(UserService.class);
        bind(ArticleService.class);
        bind(EnvironmentService.class);
    }
}
