package seguros.producto.gestionarproducto.validators;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.RecargoPorAseguradoDto;
import seguros.producto.gestionarproducto.dto.TramoDto;

public class RecargoPorAseguradoValidator implements ConstraintValidator<RecargoPorAseguradoConstraint, Object> {

    public static final String MENSAJE_ERROR_NOT_VALID= "El campo valorHasta (Hasta asegurado) debe ser mayor que el campo valorDesde (Desde asegurado)  ";
    public static final String MENSAJE_ERROR_NOT_INTEGER_DESDE_ASEGURADO= "El campo valorDesde (Desde asegurado) debe ser un valor entero";
    public static final String MENSAJE_ERROR_NOT_INTEGER_HASTA_ASEGURADO= "El campo valorHasta (Hasta asegurado) debe ser un valor entero";
    public static final String MENSAJE_ERROR_NOT_INTEGER_PORCENTAJE_RECARGO= "El campo valorRecargo (Porcentaje de recargo) debe ser un valor entero";

    public static final String fieldNameValorDesde= "desdeAsegurado";
    public static final String fieldNameValorHasta= "hastaAsegurado";
    public static final String fieldNameValorRecargo= "porcentajeRecargo";

    @Override
    public void initialize(RecargoPorAseguradoConstraint recargoPorAseguradoConstraint) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
        RecargoPorAseguradoDto recargoPorAseguradoDto = (RecargoPorAseguradoDto) obj;


        Integer valorDesde= recargoPorAseguradoDto.getDesdeAsegurado()==null?new Integer(0):recargoPorAseguradoDto.getDesdeAsegurado();
        Integer valorHasta= recargoPorAseguradoDto.getHastaAsegurado()==null?new Integer(0):recargoPorAseguradoDto.getHastaAsegurado();
        BigDecimal valorRecargo= recargoPorAseguradoDto.getPorcentajeRecargo()==null?new BigDecimal(0):recargoPorAseguradoDto.getPorcentajeRecargo();

        try {
            if(valorDesde==null) {
                customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_DESDE_ASEGURADO,fieldNameValorDesde);
                return false;
            }

        } catch (ArithmeticException ex) {
            customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_DESDE_ASEGURADO,fieldNameValorDesde);
            return false;
        }
        try {
            if(valorHasta==null) {
                customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_HASTA_ASEGURADO,fieldNameValorHasta);
                return false;
            }

        } catch (ArithmeticException ex) {
            customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_HASTA_ASEGURADO,fieldNameValorHasta);
            return false;
        }
        try {
            if(valorRecargo==null) {
                customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_PORCENTAJE_RECARGO,fieldNameValorRecargo);
                return false;
            }

        } catch (ArithmeticException ex) {
            customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_PORCENTAJE_RECARGO,fieldNameValorRecargo);
            return false;
        }
        if(valorHasta.compareTo(valorDesde) < 0 ) {
            customMessageForValidation(cxt,MENSAJE_ERROR_NOT_VALID,fieldNameValorHasta);
            return false;
        }

        return true;
    }

    private void customMessageForValidation(ConstraintValidatorContext cxt, String message, String field) {
        cxt.disableDefaultConstraintViolation();
        cxt.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }
}
