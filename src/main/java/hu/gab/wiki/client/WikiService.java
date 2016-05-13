package hu.gab.wiki.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import hu.gab.wiki.shared.dto.DTO_User;

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

    List<DTO_User> listUsers();

    void addNewUser(String name, String email, String password);
}