package bg.softuni.playing_cards_shop.models.validations;

import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldValidator.class)
public @interface UniqueField {

    FieldType fieldType();

    String message() default "User with such property already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
