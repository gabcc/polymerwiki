package hu.gab.wiki.server.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.gab.wiki.server.service.*;

/**
 * @author PG
 * @since 2016-05-20
 * <p>
 * IOC container szerver oldalhoz.
 */
public class Container {

    private AuthService authService;
    private UserService userService;
    private ArticleService articleService;
    private EnvironmentService environmentService;
    private CategotyService categotyService;

    public void init() {
        Injector injector = Guice.createInjector(new WikiModule());

        authService = injector.getInstance(AuthService.class);
        userService = injector.getInstance(UserService.class);
        articleService = injector.getInstance(ArticleService.class);
        environmentService = injector.getInstance(EnvironmentService.class);
        categotyService = injector.getInstance(CategotyService.class);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public UserService getUserService() {
        return userService;
    }

    public CategotyService getCategotyService() {
        return categotyService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public EnvironmentService getEnvironmentService() {
        return environmentService;
    }
}
