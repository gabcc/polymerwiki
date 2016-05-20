package hu.gab.wiki.server.service;

import com.google.inject.Inject;
import hu.gab.wiki.server.DTO;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_Article;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.entity.Article;
import hu.gab.wiki.server.entity.ArticleVersion;
import hu.gab.wiki.server.entity.Environment;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.status.ContentStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-20
 */
public class ArticleService {

    private EnvironmentService environmentService;

    @Inject
    public ArticleService(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public List<DTO_ArticleSummary> listArticleSummaries(){
        List<DTO_ArticleSummary> result = new DBTemplate<List<DTO_ArticleSummary>>((session, template) -> {
            List<ArticleVersion> list = DAO_Article.list(session);
            template.setResult(list.stream().map(DTO.instance::dtoArticleSummary).collect(Collectors.toList()));
        }).getResult();

        return result;
    }

    //TODO ez itt már hák, ezt majd szépen megcsinálni
    public void createArticle(final DTO_ArticleSummary articleSummary, DTO_User user){
        new DBTemplate<Void>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                Article article = new Article();
                article.setSlug(articleSummary.getSlug());
                article.setCreated(new Date());
                article.setStatus(ContentStatus.PUBLISHED);

                Environment defEnv = environmentService.getDefault();
                article.setEnvironment(defEnv);

                ArticleVersion articleVersion = new ArticleVersion();
                articleVersion.setCreated(article.getCreated());
                articleVersion.setArticle(article);
                articleVersion.setName(articleSummary.getCurrentName());

                User user1 = DAO_User.get(session1, user.getId());
                articleVersion.setCreator(user1);

                article.getVersions().add(articleVersion);

                session.save(article);
            });
        });
    }
}
