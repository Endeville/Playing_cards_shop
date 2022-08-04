package bg.softuni.playing_cards_shop.models.validations;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        this.first=constraintAnnotation.first();
        this.second=constraintAnnotation.second();
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        var first=beanWrapper.getPropertyValue(this.first);
        var second=beanWrapper.getPropertyValue(this.second);

        var valid=false;

        if(first==null){
            valid= second==null;
        }else{
            valid=first.equals(second);
        }

        if(!valid){
            context
                    .buildConstraintViolationWithTemplate(this.message)
                    .addPropertyNode(this.second)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
