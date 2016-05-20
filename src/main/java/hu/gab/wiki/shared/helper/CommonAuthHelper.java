package hu.gab.wiki.shared.helper;

import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class CommonAuthHelper {
    public static final CommonAuthHelper instance = new CommonAuthHelper();

    private CommonAuthHelper() {

    }

    /**
     * Helper függvény annak az eldöntésére, hogy a kapott jogokat birtokolja-e a bejelentkezett user.
     *
     * @param requiredRoles - a szükséges roleok
     * @return
     */
    public static boolean isEligibleForAction(List<String> requiredRoles, List<String> currentRoles) {
        if (requiredRoles.size() == 0) {
            return true;
        }

        boolean hasAllRoles = true;

        for (String requiredRole : requiredRoles) {
            if (!currentRoles.contains(requiredRole)) {
                hasAllRoles = false;
            }
        }

        return hasAllRoles;
    }

    public static List<String> getRolesOfUser(DTO_User user) {
        List<String> result = new ArrayList<>();

        List<DTO_Role> roles = user.getRoles();
        for (DTO_Role role : roles) {
            result.add(role.getName());
        }

        return result;
    }
}
