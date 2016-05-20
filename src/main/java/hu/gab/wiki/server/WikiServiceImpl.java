package hu.gab.wiki.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.ioc.Container;
import hu.gab.wiki.shared.dto.DTO_LoginData;
import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.exceptions.CommonWikiException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-12
 */
public class WikiServiceImpl extends RemoteServiceServlet implements WikiService {

    private Container container;

    public WikiServiceImpl() {
        container = new Container();
        container.init();
    }

    @Override
    public List<DTO_User> listUsers(DTO_LoginData authData) {
        authAndAuth(authData, ServerCLC.Auth.LIST_USERS_ROLES);

        List<DTO_User> users = new DBTemplate<List<DTO_User>>((session, template) -> {
            List<User> tempUsers = DAO_User.list(session);
            template.setResult(
                    tempUsers.stream().map(DTO.instance::dto).collect(Collectors.toList())
            );
        }).getResult();

        return users;
    }

    @Override
    public void addNewUser(DTO_LoginData authData, String name, String email, String password) throws CommonWikiException {
        authAndAuth(authData, ServerCLC.Auth.ADD_UPDATE_USER_ROLES);

        if (name == null || email == null || password == null) {
            throw new RuntimeException("Nincs megadva egy szükséges elem!");
        }

        try {
            container.getUserService().addNewUser(name, email, password);
        } catch (Throwable r) {
            throw new CommonWikiException(r.getMessage());
        }

    }

    @Override
    public void updateUser(DTO_LoginData authData, DTO_User user) throws CommonWikiException {
        authAndAuth(authData, ServerCLC.Auth.ADD_UPDATE_USER_ROLES);

        container.getUserService().updateUser(user);
    }

    @Override
    public List<DTO_Role> getRoles(DTO_LoginData authData) {
        authAndAuth(authData, ServerCLC.Auth.ADD_UPDATE_USER_ROLES);

        return container.getUserService().getRoles();
    }

    @Override
    public DTO_LoginData login(final String email, final String password) throws CommonWikiException {
        if (email == null || password == null) {
            throw new CommonWikiException("Nincs megadva vagy az email vagy a password");
        }

        try {
            DTO_Token token = container.getAuthService().login(email, password);
            DTO_User user = new DBTemplate<DTO_User>((session, template) -> {
                template.setResult(
                        DTO.instance.dto(DAO_User.findByEmail(session, email))
                );
            }).getResult();

            DTO_LoginData loginData = new DTO_LoginData();
            loginData.setUser(user);
            loginData.setToken(token);

            return loginData;
        } catch (Throwable t) {
            throw new CommonWikiException(t.getMessage());
        }
    }

    @Override
    public List<DTO_ArticleSummary> listArticleSummaries() {
        return container.getArticleService().listArticleSummaries();
    }

    @Override
    public void addArticle(DTO_LoginData authData, DTO_ArticleSummary articleSummary) {
        authAndAuth(authData, ServerCLC.Auth.ADD_UPDATE_ARTICLES);

        container.getArticleService().createArticle(articleSummary, authData.getUser());
    }

    /**
     * Helper az authentikáció/authorizáció pároshoz.
     *
     * @param authData
     * @param requiredRoles
     */
    private void authAndAuth(DTO_LoginData authData, List<String> requiredRoles) {
        if (!(container.getAuthService().isTokenValid(authData.getToken(), authData.getUser()) && container.getAuthService().isAuthorized(requiredRoles, authData.getUser()))) {
            throw new RuntimeException("Ehhez itt nincs joga valakinek....");
        }
    }
}