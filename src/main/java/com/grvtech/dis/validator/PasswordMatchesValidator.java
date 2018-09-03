package com.grvtech.dis.validator;




import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.grvtech.dis.model.LoginUser;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final LoginUser user = (LoginUser) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
