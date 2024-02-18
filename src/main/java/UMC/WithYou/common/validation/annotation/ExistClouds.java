package UMC.WithYou.common.validation.annotation;

import UMC.WithYou.common.validation.validator.CloudsExistValidator;
import UMC.WithYou.common.validation.validator.NoticesExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CloudsExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistClouds {
    String message() default "해당하는 클라우드가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}