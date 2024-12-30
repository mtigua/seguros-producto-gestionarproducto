package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import seguros.producto.gestionarproducto.dto.ProductoDto;

public class CumuloValidator implements ConstraintValidator<CumuloConstraint, Object> {
	
	
	public static final String MENSAJE_ERROR_REQUIRED_MESES_PERMANENCIA = "El campo mesesPermanencia (Meses de Permanencia) es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MESES_CASTIGO = "El campo mesesCastigo (Meses Castigo) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_MESES_PERMANENCIA = "El campo mesesPermanencia (Meses de Permanencia) debe ser nulo o 0";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_MESES_CASTIGO = "El campo mesesCastigo (Meses Castigo) debe ser nulo o 0";

	public static final String fieldNameMesesPermanencia= "mesesPermanencia";
	public static final String fieldNameMesesCastigo= "mesesCastigo";

	
    @Override
    public void initialize(CumuloConstraint cumulo) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    
    	
    	Boolean aseguradoCumulo= productoDto.getAseguradoCumulo()==null?false:productoDto.getAseguradoCumulo();
    	Integer mesesPermanencia= productoDto.getMesesPermanencia()==null?0:productoDto.getMesesPermanencia();
    	Integer mesesCastigo= productoDto.getMesesCastigo()==null?0:productoDto.getMesesCastigo();
    
    	
    	if(aseguradoCumulo) {
    		if(mesesPermanencia <= 0 ) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MESES_PERMANENCIA,fieldNameMesesPermanencia); 
    			return false;
    		}
    		if(mesesCastigo <= 0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MESES_CASTIGO,fieldNameMesesCastigo); 
    			return false;
    		}
    	}
    	else {
    		if(mesesPermanencia > 0 ) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED_MESES_PERMANENCIA,fieldNameMesesPermanencia); 
    			return false;
    		}
    		if(mesesCastigo > 0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED_MESES_CASTIGO,fieldNameMesesCastigo); 
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
