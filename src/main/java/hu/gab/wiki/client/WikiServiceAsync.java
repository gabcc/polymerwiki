package hu.gab.wiki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import hu.gab.wiki.shared.dto.DTO_LoginData;
import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public interface WikiServiceAsync {
    void listUsers(AsyncCallback<List<DTO_User>> async);

    void addNewUser(String name, String email, String password, AsyncCallback<Void> async);

    void getRoles(AsyncCallback<List<DTO_Role>> async);

    void updateUser(DTO_User user, AsyncCallback<Void> async);

    void login(String email, String password, AsyncCallback<DTO_LoginData> async);
}
