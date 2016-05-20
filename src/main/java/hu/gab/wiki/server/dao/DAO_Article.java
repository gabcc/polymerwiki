package hu.gab.wiki.server.dao;

import hu.gab.wiki.server.entity.Article;
import hu.gab.wiki.server.entity.ArticleVersion;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-20
 */
public class DAO_Article {
    public static List<ArticleVersion> list(Session session) {
        List<Article> list = (List<Article>) session.createCriteria(Article.class).list();

        List<ArticleVersion> latestVersions = new ArrayList<>();

        for (Article article : list) {
            ArticleVersion max = Collections.max(article.getVersions(), (o1, o2) -> (o1.getId() < o2.getId()) ? -1 : 1);
            latestVersions.add(max);
        }

        return latestVersions;
    }
}
