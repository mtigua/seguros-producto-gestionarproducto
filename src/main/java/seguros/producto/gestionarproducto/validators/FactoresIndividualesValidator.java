package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class FactoresIndividualesValidator implements ConstraintValidator<FactoresIndividualesConstraint, Object> {
	
	public static final String MENSAJE_ERROR_REQUIRED = "El campo tipoScoring (Centro Costo Cía) debe ser marcado";
	public static final String FIELD_TIPO_SCORING= "tipoScoring";
	
    @Override
    public void initialize(FactoresIndividualesConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    
    	Boolean usaFactIndiv= productoDto.getUsaFactIndiv()==null?false:productoDto.getUsaFactIndiv();
    	Boolean tipoScoring= productoDto.getTipoScoring()==null?false:productoDto.getTipoScoring();
    
    	if(usaFactIndiv) {
    		if(!tipoScoring) {
    			customMessageForValidation(cxt, MENSAJE_ERROR_REQUIRED,FIELD_TIPO_SCORING);
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
