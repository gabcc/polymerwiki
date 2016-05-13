package hu.gab.wiki.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import hu.gab.wiki.client.WikiService;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserVersion;
import hu.gab.wiki.server.service.UserService;
import hu.gab.wiki.shared.dto.DTO_User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-12
 */
public class WikiServiceImpl extends RemoteServiceServlet implements WikiService {
    @Override
    public List<DTO_User> listUsers() {
        List<User> users = new DBTemplate<List<User>>((session, template) -> {
            template.setResult(DAO_User.list(session));
        }).getResult();

        return users.stream().map(DTO::dto).collect(Collectors.toList());
    }

    @Override
    public void addNewUser(String name, String email, String password) {
        if(name == null || email == null || password == null){
            throw new RuntimeException("Nincs megadva egy szükséges elem!");
        }

        UserService.addNewUser(name, email, password);
    }
}