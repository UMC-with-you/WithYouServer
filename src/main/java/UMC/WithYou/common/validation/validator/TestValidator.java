package UMC.WithYou.common.validation.validator;

import UMC.WithYou.common.validation.annotation.TestAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TestValidator implements ConstraintValidator<TestAnnotation, Long> {
    @Override
    public void initialize(TestAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return false;
    }
}
