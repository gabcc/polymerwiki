package hu.gab.wiki.server;

import hu.gab.wiki.server.entity.*;
import hu.gab.wiki.shared.dto.DTO_Token;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-12
 */
public class DTO {
    public static final DTO instance = new DTO();

    /**
     * Admin felülethez user dto.
     * A legutolsó user versiont csapja hozzá, hiszen az az aktuális.
     *
     * @param user
     * @return
     */
    public DTO_User dto(User user) {
        DTO_User dto_user = new DTO_User();
        dto_user.setId(user.getId());
        dto_user.setName(user.getName());
        dto_user.setEmail(user.getEmail());
        dto_user.setStatus(user.getStatus());

        List<UserVersion> versions = user.getVersions();
        UserVersion userVersion = versions.get(versions.size() - 1);

        dto_user.setRoles(
                userVersion.getRoles().stream().map(DTO.instance::dto).collect(Collectors.toList())
        );

        return dto_user;
    }

    public DTO_Role dto(Role role) {
        DTO_Role dto_role = new DTO_Role();
        dto_role.setId(role.getId());
        dto_role.setName(role.getName());

        return dto_role;
    }

    public DTO_Token dto(UserToken token){
        DTO_Token dto_token = new DTO_Token();
        dto_token.setToken(token.getToken());

        return dto_token;
    }

    public DTO_ArticleSummary dtoArticleSummary(ArticleVersion articleVersion){
        long id = articleVersion.getArticle().getId();
        String name = articleVersion.getName();
        String slug = articleVersion.getArticle().getSlug();

        DTO_ArticleSummary dto_articleSummary = new DTO_ArticleSummary();
        dto_articleSummary.setId(id);
        dto_articleSummary.setSlug(slug);
        dto_articleSummary.setCurrentName(name);

        return dto_articleSummary;
    }


}
