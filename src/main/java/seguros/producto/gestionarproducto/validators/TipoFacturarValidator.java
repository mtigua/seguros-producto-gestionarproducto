package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class TipoFacturarValidator implements ConstraintValidator<TipoFacturarConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo persCodigoFactSingle (Facturar a) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED= "El campo persCodigoFactSingle (Facturar a) debe ser vacio o null";
	public static final String FIELD_PERS_CODIGO_FACT= "persCodigoFactSingle";
	
    @Override
    public void initialize(TipoFacturarConstraint adjunto) {
    }

    
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	Long tipoFacturar= productoDto.getTipoFacturar()==null?0L:productoDto.getTipoFacturar();
    	String persCodigoFactSingle= productoDto.getPersCodigoFactSingle()==null?"":productoDto.getPersCodigoFactSingle().trim();
    	
    	if(tipoFacturar==1L) {
    		if(persCodigoFactSingle.length()==0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_PERS_CODIGO_FACT);
        		return false;
    		}
    	}
    	else {
    		if(persCodigoFactSingle.length()>0) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED,FIELD_PERS_CODIGO_FACT);
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
