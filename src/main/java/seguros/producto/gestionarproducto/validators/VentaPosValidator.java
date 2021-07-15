package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class VentaPosValidator implements ConstraintValidator<VentaPosConstraint, Object> {

	public static final String MENSAJE_ERROR_REQUIRED = "El campo codigoPos (Código en POS) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED = "El campo codigoPos (Código en POS) debe ser null o 0 ";
	public static final String FIELD_CODIGO_POD="codigoPos";
	
    @Override
    public void initialize(VentaPosConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	Boolean ventaPos = productoDto.getVentaPos()==null?false:productoDto.getVentaPos();
    	Integer codigoPos= productoDto.getCodigoPos()==null?0:productoDto.getCodigoPos();
    	
    	
    	if(ventaPos) {
    		if( codigoPos <= 0 ) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED,FIELD_CODIGO_POD); 
    			return false;
    		}
    	}
    	else {
    		if( codigoPos > 0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED,FIELD_CODIGO_POD); 
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
