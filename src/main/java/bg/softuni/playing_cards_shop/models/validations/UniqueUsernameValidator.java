package bg.softuni.playing_cards_shop.models.validations;

import bg.softuni.playing_cards_shop.services.interfaces.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;
    private String message;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username==null){
            return true;
        }

        return !userService.usernameExists(username);
    }
}
