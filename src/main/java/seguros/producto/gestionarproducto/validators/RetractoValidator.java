package seguros.producto.gestionarproducto.validators;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class RetractoValidator implements ConstraintValidator<RetractoConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED_DIAS = "El campo diasRetracto (Plazo de días) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_DIAS= "El campo diasRetracto (Plazo de días)  debe ser 0 o null";
	public static final String MENSAJE_ERROR_REQUIRED_VALOR = "El campo valorRetracto (Valor multa por retracto) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_VALOR= "El campo valorRetracto (Valor multa por retracto) deben ser 0 o null";
	public static final String FIELD_DIAS= "diasRetracto";
	public static final String FIELD_VALOR= "valorRetracto";
	
    @Override
    public void initialize(RetractoConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	Boolean retracto= productoDto.getRetracto()==null?false:productoDto.getRetracto();
    	Integer diasRetracto= productoDto.getDiasRetracto()==null?0:productoDto.getDiasRetracto();
    	BigDecimal valorRetracto= productoDto.getValorRetracto()==null?new BigDecimal(0):productoDto.getValorRetracto();
   // 	String monedaRetracto= productoDto.getMonedaRetracto();
    	//String domiMoneCod= productoDto.getDomiMoneCod();
    	
    	if(retracto) {
    		if(diasRetracto <= 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_DIAS,FIELD_DIAS);
    			return false;
    		}
    		if(valorRetracto.signum() == 0)  {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED_VALOR,FIELD_VALOR);
    			return false;
    		}
    	}
    	else {
    		if(diasRetracto > 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED_DIAS,FIELD_DIAS);
    			return false;
    		}
    		if(valorRetracto.signum() > 0)  {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED_VALOR,FIELD_VALOR);
    			return false;
    		}
    	}
    	return true;
    }
    
    private void customMessageForValidation(ConstraintValidatorContext cxt, String message, String field) {
    	cxt.disableDefaultConstraintViolation();
    	cxt.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }
    
}
