package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class TerminosCortoValidator implements ConstraintValidator<TerminosCortoConstraint, Object> {
	
  public static final String MENSAJE_ERROR_REQUIRED = "El campo tcPermMin (Permanencia mínima) es requerido";
  public static final String MENSAJE_ERROR_NOT_REQUIRED= "El campo tcPermMin (Permanencia mínima) debe ser 0 o null";
  public static final String FIELD_TC_PERM= "tcPermMin";
  
    @Override
    public void initialize(TerminosCortoConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    
    	Boolean aplicaTc= productoDto.getAplicaTc()==null?false:productoDto.getAplicaTc();
    	Integer tcPermMin= productoDto.getTcPermMin()==null?0:productoDto.getTcPermMin();
    
    	if(aplicaTc) {
    		if( tcPermMin <= 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_TC_PERM);
    			return false;
    		}
    	}
    	else {
    		if( tcPermMin > 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED,FIELD_TC_PERM);
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
