package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class TarifaValidator implements ConstraintValidator<TarifaConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo tipoTarifa (Se aplica) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED= "El campo tipoTarifa (Se aplica) debe ser null o -1";
	public static final String FIELD_TIPO_TARIFA="tipoTarifa";
	
    @Override
    public void initialize(TarifaConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	
    	Long tarifaPor= productoDto.getTarifaPor()==null?0L:productoDto.getTarifaPor();
    	Long tipoTarifa= productoDto.getTipoTarifa()==null?0L:productoDto.getTipoTarifa();
    	
    	if(tarifaPor==1L) {
    		if(tipoTarifa == 0L || tipoTarifa== -1L ) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_TIPO_TARIFA);
    			return false;
    		}
    	}
    	else {
    		if(tipoTarifa > 0L) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_NOT_REQUIRED,FIELD_TIPO_TARIFA);
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
