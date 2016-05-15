package hu.gab.wiki.client.helper;

import com.vaadin.polymer.paper.widget.PaperInput;

/**
 * @author PG
 * @since 2016-05-15
 */
public class UserAdminHelper {
    public static boolean validateName(PaperInput input){
        if(input == null) throw new RuntimeException("Nem volt megadott paper input!");

        String name = input.getValue();

        if (name == null || name.length() == 0) {
            input.setErrorMessage("name is missing");
            input.setInvalid(true);
            return false;
        } else {
            input.setInvalid(false);
            return true;
        }
    }

    public static boolean validateEmail(PaperInput input){
        if(input == null) throw new RuntimeException("Nem volt megadott paper input!");

        String email = input.getValue();

        if (email == null || email.length() == 0) {
            input.setErrorMessage("Email is not valid");
            input.setInvalid(true);
            return false;
        } else {
            input.setInvalid(false);
            return true;
        }
    }

    public static boolean validatePassword(PaperInput input){
        if(input == null) throw new RuntimeException("Nem volt megadott paper input!");

        String password = input.getValue();

        if (password == null || password.length() == 0) {
            input.setErrorMessage("password is missing");
            input.setInvalid(true);
            return false;
        } else {
            input.setInvalid(false);
            return true;
        }
    }
}
