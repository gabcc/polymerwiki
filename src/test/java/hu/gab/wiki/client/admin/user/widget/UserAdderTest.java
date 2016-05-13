package hu.gab.wiki.client.admin.user.widget;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import hu.gab.wiki.client.ClientCLC;
import org.junit.Test;

/**
 * @author PG
 * @since 2016-05-13
 */
public class UserAdderTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "hu.gab.wiki.root";
    }

    @Test
    public void testRegexp() {
        String mailBad = "aasdsad";
        String mailFine = "g.percze@gmail.com";

        RegExp regExp = RegExp.compile(ClientCLC.UserAdminActivity.EMAIL_REGEXP);
        MatchResult result1 = regExp.exec(mailBad);
        MatchResult result2 = regExp.exec(mailFine);


        if(result1 != null) throw new RuntimeException("Ennek hibára kellene futnia");
        if(result2 == null) throw new RuntimeException("Ennek jónak kellett volna lennie");
    }
}