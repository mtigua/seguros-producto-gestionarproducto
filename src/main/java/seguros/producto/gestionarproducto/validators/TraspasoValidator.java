package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import seguros.producto.gestionarproducto.dto.ProductoDto;

public class TraspasoValidator implements ConstraintValidator<TraspasoConstraint, Object> {

	public static final String MENSAJE_ERROR_REQUIRED = "El campo docuNroPoliza (Número póliza) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED = "El campo docuNroPoliza (Número póliza) debe ser vacio o null";
	public static final String FIELD_DOCU_NRO= "docuNroPoliza";	

	
    @Override
    public void initialize(TraspasoConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    
    	Long tipoTraspaso= productoDto.getTipoTraspaso()==null?0L:productoDto.getTipoTraspaso();
    	String docuNroPoliza= productoDto.getDocuNroPoliza()==null?"":productoDto.getDocuNroPoliza().trim();
    	
    	if(tipoTraspaso== 1L) {
    		
    		 if(docuNroPoliza.trim().length()==0) {
    				customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED,FIELD_DOCU_NRO); 
        			return false;
    			}
    		
    	}
    	else {    	
    		if(docuNroPoliza.trim().length() > 0) {
				customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED,FIELD_DOCU_NRO); 
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
