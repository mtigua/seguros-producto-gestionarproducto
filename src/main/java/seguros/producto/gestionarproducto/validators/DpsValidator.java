package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import seguros.producto.gestionarproducto.dto.ProductoDto;

public class DpsValidator implements ConstraintValidator<DpsConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED_DPS = "El campo planDpsTextos (Mensaje Pop-Up) es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MAX_CANT_ASEGURADO = "El campo maxCantAsegurados (Máxima cantidad de asegurados) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_DPS = "El campo planDpsTextos (Mensaje Pop-Up) debe ser nulo o vacio";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_MAX_CANT_ASEGURADO = "El campo maxCantAsegurados (Máxima cantidad de asegurados) debe ser 0 o nulo";
	public static final String FIELD_DPS="planDpsTextos";
	public static final String FIELD_MAX_CANT_ASEGURADO="maxCantAsegurados";
	
    @Override
    public void initialize(DpsConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	
    	Boolean declaracionPrincipal= productoDto.getDeclaracionPrincipal()==null?false:productoDto.getDeclaracionPrincipal();
    	String planDpsTextos= productoDto.getPlanDpsTextos()==null?"":productoDto.getPlanDpsTextos().trim();
    	Integer maxCantAsegurados= productoDto.getMaxCantAsegurados()==null?0:productoDto.getMaxCantAsegurados();
    	
    	if(declaracionPrincipal) {
    		if(planDpsTextos.equalsIgnoreCase("")) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_DPS,FIELD_DPS);
        		return false;
    		}
    		if( maxCantAsegurados<=0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MAX_CANT_ASEGURADO,FIELD_MAX_CANT_ASEGURADO);
    			return false;
    		}
    	}
    	else {    		
    		if(!planDpsTextos.equalsIgnoreCase("")) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED_DPS,FIELD_DPS);
        		return false;
    		}
    		if( maxCantAsegurados>0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED_MAX_CANT_ASEGURADO,FIELD_MAX_CANT_ASEGURADO);
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
