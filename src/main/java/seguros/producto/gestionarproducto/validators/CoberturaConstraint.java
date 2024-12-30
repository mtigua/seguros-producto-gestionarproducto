package seguros.producto.gestionarproducto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CoberturaValidator.class)
@Target( ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CoberturaConstraint {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
