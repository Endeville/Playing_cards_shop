package bg.softuni.playing_cards_shop.models.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsMatchValidator.class)
public @interface FieldsMatch {

    String first();

    String second();

    String message() default "Fields must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
