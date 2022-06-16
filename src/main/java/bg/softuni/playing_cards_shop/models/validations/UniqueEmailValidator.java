package bg.softuni.playing_cards_shop.models.validations;

import bg.softuni.playing_cards_shop.services.interfaces.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;
    private String message;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email==null){
            return true;
        }

        return !userService.emailExists(email);
    }
}
