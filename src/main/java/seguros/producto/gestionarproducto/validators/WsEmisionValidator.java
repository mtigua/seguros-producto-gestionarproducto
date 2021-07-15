package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;



public class WsEmisionValidator implements ConstraintValidator<WsEmisionConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo indiqueCodigoEmisionWs (Indique código de WS Asociado a la Emisión) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED = "El campo indiqueCodigoEmisionWs (Indique código de WS Asociado a la Emisión) debe ser null o -1 ";
	public static final String FIELD_INDIQUE_CODIGO_EMISION="indiqueCodigoEmisionWs";
	
    @Override
    public void initialize(WsEmisionConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	
    	Boolean usaWsEmision= productoDto.getUsaWsEmision()==null?false:productoDto.getUsaWsEmision();
    	Integer indiqueCodigoEmisionWs= productoDto.getIndiqueCodigoEmisionWs()==null?0:productoDto.getIndiqueCodigoEmisionWs();
    	
    	if(usaWsEmision) {
    		if(indiqueCodigoEmisionWs<=0 ) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_INDIQUE_CODIGO_EMISION);
    			return false;
    		}
    	}
    	else {
    		if(indiqueCodigoEmisionWs > 0 ) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,MENSAJE_ERROR_NOT_REQUIRED);
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
