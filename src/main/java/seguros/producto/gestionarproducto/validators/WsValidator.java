package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class WsValidator implements ConstraintValidator<WsConstraint, Object> {
	public static final String MENSAJE_ERROR_REQUIRED = "El campo indiqueCodigoWs (Indique código de WS Asociado) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED  = "El campo indiqueCodigoWs (Indique código de WS Asociado) debe ser null o -1 ";
	public static final String FIELD_INDIQUE_CODIGO_WS="indiqueCodigoWs";
	
    @Override
    public void initialize(WsConstraint adjunto) {
    	
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	
    	Boolean usaWs= productoDto.getUsaWs()==null?false:productoDto.getUsaWs();
    	Integer indiqueCodigoWs= productoDto.getIndiqueCodigoWs()==null?0:productoDto.getIndiqueCodigoWs();
    	
    	if(usaWs) {
    		if( indiqueCodigoWs<=0 ) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_INDIQUE_CODIGO_WS);
    			return false;
    		}
    	}
    	else {
    		if( indiqueCodigoWs >0 ) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED,FIELD_INDIQUE_CODIGO_WS);
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
