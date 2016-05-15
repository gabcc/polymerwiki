package hu.gab.wiki.client.helper;

import hu.gab.wiki.client.AppUtils;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class AuthHelper {
    public static final AuthHelper instance = new AuthHelper();

    private AuthHelper() {

    }

    /**
     * Helper függvény annak az eldöntésére, hogy a kapott jogokat birtokolja-e a bejelentkezett user.
     * @param requiredRoles - a szükséges roleok
     * @return
     */
    public boolean isEligibleForAction(List<String> requiredRoles) {
        if (requiredRoles.size() == 0) {
            return true;
        }

        DTO_User user = AppUtils.getClientFactory().getClientStore().getUser();
        if (user != null) {
            List<DTO_Role> roles = user.getRoles();
            List<String> roleNames = new ArrayList<>();
            for (DTO_Role role : roles) {
                roleNames.add(role.getName());
            }

            boolean hasAllRoles = true;

            for (String requiredRole : requiredRoles) {
                if (!roleNames.contains(requiredRole)) {
                    hasAllRoles = false;
                }
            }

            return hasAllRoles;
        }
        else{
            return false;
        }
    }
}
