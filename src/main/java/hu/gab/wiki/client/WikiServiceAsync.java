package hu.gab.wiki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import hu.gab.wiki.shared.dto.DTO_LoginData;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public interface WikiServiceAsync {
    void login(String email, String password, AsyncCallback<DTO_LoginData> async);

    void listUsers(DTO_LoginData authData, AsyncCallback<List<DTO_User>> async);

    void addNewUser(DTO_LoginData authData, String name, String email, String password, AsyncCallback<Void> async);

    void updateUser(DTO_LoginData authData, DTO_User user, AsyncCallback<Void> async);

    void getRoles(DTO_LoginData authData, AsyncCallback<List<DTO_Role>> async);

    void addArticle(DTO_LoginData authData, DTO_ArticleSummary articleSummary, AsyncCallback<Void> async);

    void listArticleSummaries(AsyncCallback<List<DTO_ArticleSummary>> async);

    void updateUsers(DTO_LoginData authData, List<DTO_User> users, AsyncCallback<Void> async);
}
