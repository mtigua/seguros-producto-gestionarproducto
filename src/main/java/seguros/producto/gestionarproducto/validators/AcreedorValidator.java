package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class AcreedorValidator implements ConstraintValidator<AcreedorConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo persCodigoAcre (Acreedor) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED= "El campo persCodigoAcre (Acreedor) debe ser vacio o null";
	public static final String fieldName="persCodigoAcre";
   
	
	@Override
    public void initialize(AcreedorConstraint acreedor) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	
    	Long tipoAcreedor= productoDto.getTipoAcreedor()==null?0L:productoDto.getTipoAcreedor();
    	String persCodigoAcre = productoDto.getPersCodigoAcre()==null?"":productoDto.getPersCodigoAcre();
    	
    	
    	if(tipoAcreedor == 1L) {
    		 if( persCodigoAcre.trim().length() == 0 ) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED,fieldName); 
    			return false;
    		}
    	}
    	else {    	
    		 if( persCodigoAcre.trim().length() > 0 ) {
    			 customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED,fieldName); 
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
