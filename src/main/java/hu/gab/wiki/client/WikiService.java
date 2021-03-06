package hu.gab.wiki.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import hu.gab.wiki.shared.dto.DTO_LoginData;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.exceptions.CommonWikiException;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
@RemoteServiceRelativePath("WikiService")
public interface WikiService extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use WikiService.App.getInstance() to access static instance of WikiServiceAsync
     */
    public static class App {
        private static final WikiServiceAsync ourInstance = (WikiServiceAsync) GWT.create(WikiService.class);

        public static WikiServiceAsync getInstance() {
            return ourInstance;
        }
    }

    List<DTO_User> listUsers(DTO_LoginData authData);

    void addNewUser(DTO_LoginData authData, String name, String email, String password) throws CommonWikiException;

    void updateUser(DTO_LoginData authData, DTO_User user) throws CommonWikiException;

    void updateUsers(DTO_LoginData authData, List<DTO_User> users) throws CommonWikiException;

    List<DTO_Role> getRoles(DTO_LoginData authData);

    DTO_LoginData login(String email, String password) throws CommonWikiException;

    List<DTO_ArticleSummary> listArticleSummaries();

    void addArticle(DTO_LoginData authData, DTO_ArticleSummary articleSummary);
}
