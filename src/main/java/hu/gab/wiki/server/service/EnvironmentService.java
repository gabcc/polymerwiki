package hu.gab.wiki.server.service;

import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.entity.Environment;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author PG
 * @since 2016-05-20
 */
public class EnvironmentService {
    public Environment getDefault(){
        Environment result = new DBTemplate<Environment>((session, template) -> {
            Environment environment = ((List<Environment>) session.createCriteria(Environment.class).
                    add(Restrictions.eq("name", "dev")).list()).get(0);

            template.setResult(environment);
        }).getResult();

        return result;
    }
}
