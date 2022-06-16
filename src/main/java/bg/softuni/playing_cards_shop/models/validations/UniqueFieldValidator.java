package bg.softuni.playing_cards_shop.models.validations;

import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

    private final UserService userService;

    private FieldType fieldType;
    private String message;

    public UniqueFieldValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.fieldType = constraintAnnotation.fieldType();
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        var result=false;

        try {
            var clazz = UserService.class;

            var existsMethod = clazz.getDeclaredMethod(String.format("%sExists", this.fieldType.getName()), String.class);
            existsMethod.setAccessible(true);

            result= (boolean) existsMethod.invoke(userService,value);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if(result){
            context
                    .buildConstraintViolationWithTemplate(String.format("User with such %s already exists", this.fieldType.getName()))
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

        }

        return !result;
    }
}
