package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserToken;
import hu.gab.wiki.server.service.UserService;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DAO_UserToken {
    public static void delete(Session session, UserToken token){
        session.delete(token);
    }

    public static void clearPrevious(Session session, User user){
        List<UserToken> oldTokens = (List<UserToken>) session.createCriteria(UserToken.class).
                add(Restrictions.eq("user", user)).list();

        for(UserToken token : oldTokens){
            token.setExpired(true);
            session.update(token);
        }
    }

    public static void testDeleteAll(Session session){
        List<UserToken> list = (List<UserToken>) session.createCriteria(UserToken.class).list();
        for(UserToken token : list){
            session.delete(token);
        }
    }

    public static UserToken createToken(Session session, User user, UserService userService){
        UserToken userToken = new UserToken();
        userToken.setExpired(false);
        userToken.setCreated(new Date());
        userToken.setLastAction(new Date());
        userToken.setUser(user);

        String token = userService.createToken();
        userToken.setToken(token);

        session.save(userToken);

        return userToken;
    }

    /**
     * Az adott userhez tartozó aktuális tokent adja vissza. nullal tér vissza, ha nincs aktív token.
     * @return
     */
    public static UserToken getActiveToken(Session session, User user){
        List<UserToken> tokens = (List<UserToken>) session.createCriteria(UserToken.class)
                .add(Restrictions.eq("user", user))
                .addOrder(Order.desc("created")).list();

        if(tokens.size() < 1){
            return null;
        }

        UserToken userToken = tokens.get(0);
        if(userToken.isExpired()){
            return null;
        }
        else{
            if(isTokenExpired(userToken)){
                return null;
            }
            else{
                return userToken;
            }
        }
    }

    public static void refreshToken(Session session, UserToken token){
        token.setLastAction(new Date());
        session.update(token);
    }

    private static boolean isTokenExpired(UserToken token){
        Date now = new Date();
        Date lastAction = token.getLastAction();

        long diff = Math.abs(lastAction.getTime() - now.getTime()) / 5000;

        if(diff > 5000){
            return true;
        }
        else{
            return false;
        }
    }
}
