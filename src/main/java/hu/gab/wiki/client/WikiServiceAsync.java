package hu.gab.wiki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import hu.gab.wiki.shared.dto.DTO_User;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-12
 */
public interface WikiServiceAsync {
    void listUsers(AsyncCallback<List<DTO_User>> async);

    void addNewUser(String name, String email, String password, AsyncCallback<Void> async);
}
