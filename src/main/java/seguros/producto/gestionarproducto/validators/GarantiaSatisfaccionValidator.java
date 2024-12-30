package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class GarantiaSatisfaccionValidator implements ConstraintValidator<GarantiaSatisfaccionConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo diasVigenGarantiaSatis (Dias de Vigencia) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED= "El campo diasVigenGarantiaSatis (Dias de Vigencia) debe ser 0 o null";
	public static final String FIELD_DIAS_VIGENCIA= "diasVigenGarantiaSatis";
	
    @Override
    public void initialize(GarantiaSatisfaccionConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    
    	Boolean garantiaSatisfaccion= productoDto.getGarantiaSatisfaccion()==null?false:productoDto.getGarantiaSatisfaccion();
    	Integer diasVigenGarantiaSatis= productoDto.getDiasVigenGarantiaSatis()==null?0:productoDto.getDiasVigenGarantiaSatis();
    
    	if(garantiaSatisfaccion) {
    		if(diasVigenGarantiaSatis <= 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_DIAS_VIGENCIA);
    			return false;
    		}
    	}
    	else {
    		if(diasVigenGarantiaSatis > 0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED,FIELD_DIAS_VIGENCIA);
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
