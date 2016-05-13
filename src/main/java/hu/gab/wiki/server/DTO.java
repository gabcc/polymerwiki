package hu.gab.wiki.server;

import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.shared.dto.DTO_User;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DTO {
    public static DTO_User dto(User user){
        DTO_User dto_user = new DTO_User();
        dto_user.setId(user.getId());
        dto_user.setName(user.getName());
        dto_user.setEmail(user.getEmail());

        return dto_user;
    }
}
