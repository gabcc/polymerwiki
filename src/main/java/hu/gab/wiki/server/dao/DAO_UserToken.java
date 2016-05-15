package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserToken;
import hu.gab.wiki.server.service.UserService;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DAO_UserToken {
    public static void clearPrevious(Session session, User user){
        List<UserToken> oldTokens = (List<UserToken>) session.createCriteria(UserToken.class).
                add(Restrictions.eq("user", user)).list();

        for(UserToken token : oldTokens){
            token.setExpired(true);
            session.update(token);
        }
    }

    public static UserToken createToken(Session session, User user){
        UserToken userToken = new UserToken();
        userToken.setExpired(false);
        userToken.setCreated(new Date());
        userToken.setLastAction(new Date());
        userToken.setUser(user);

        String token = UserService.createToken();
        userToken.setToken(token);

        session.save(userToken);

        return userToken;
    }
}
