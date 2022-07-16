package bg.softuni.playing_cards_shop.models.validations;

import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {
    private final ApplicationContext appContext;

    private FieldType fieldType;
    private String message;

    public UniqueFieldValidator(ApplicationContext appContext) {
        this.appContext = appContext;
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
            var className="bg.softuni.playing_cards_shop.services.interfaces." + this.fieldType.getEntity() + "Service";
            var clazz = Class.forName(className);
            var instance=clazz.cast(appContext.getBean(clazz));

            var existsMethod = clazz.getDeclaredMethod(String.format("%sExists", this.fieldType.getName()), String.class);
            existsMethod.setAccessible(true);

            result= (boolean) existsMethod.invoke(instance,value);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(result){
            context
                    .buildConstraintViolationWithTemplate(String.format("%s with such %s already exists", this.fieldType.getEntity() ,this.fieldType.getName()))
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return !result;
    }
}
