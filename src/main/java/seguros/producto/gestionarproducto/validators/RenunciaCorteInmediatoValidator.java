package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class RenunciaCorteInmediatoValidator implements ConstraintValidator<RenunciaCorteInmediatoConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo diasRenCorInme (Dias) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED= "El campo diasRenCorInme (Dias) debe ser 0 o null";
	public static final String FIELD_DIAS="diasRenCorInme";
	
    @Override
    public void initialize(RenunciaCorteInmediatoConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	Boolean renCorInme= productoDto.getRenCorInme()==null?false:productoDto.getRenCorInme();
    	Integer diasRenCorInme= productoDto.getDiasRenCorInme()==null?0:productoDto.getDiasRenCorInme();
    	
    	if(renCorInme) {
    		if(diasRenCorInme<=0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_DIAS);
    			return false;
    		}
    	}
    	else {
    		if(diasRenCorInme > 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED,FIELD_DIAS);
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
