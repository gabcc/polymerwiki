package hu.gab.wiki.client.validator;

import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

/**
 * @author PG
 * @since 2016-05-13
 * <p>
 * Valamiért nem ment :(
 */
@Deprecated
public class ValidatorFactory extends AbstractGwtValidatorFactory {
    @Override
    public AbstractGwtValidator createValidator() {
        return null;
    }
//    public ValidatorFactory() {
//    }
//
//    @Override
//    public AbstractGwtValidator createValidator() {
//        return GWT.create(GwtValidator.class);
//    }
//
//
//    @GwtValidation(DTO_User.class)
//    public interface GwtValidator extends Validator{
//    }
}
