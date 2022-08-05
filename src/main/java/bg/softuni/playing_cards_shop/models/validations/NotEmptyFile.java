package bg.softuni.playing_cards_shop.models.validations;

import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyFileValidator.class)
public @interface NotEmptyFile {

    String message() default "File is empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
