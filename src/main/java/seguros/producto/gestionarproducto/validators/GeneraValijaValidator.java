package seguros.producto.gestionarproducto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.ProductoDto;

public class GeneraValijaValidator implements ConstraintValidator<GeneraValijaConstraint, Object> {

	public static final String MENSAJE_ERROR_REQUIRED = "Los campos genValiCm (Genera valija carga masiva) y docuPreimpreso (C.M. Genera Valija Doc. Preimpreso) no pueden estar marcados ambos al mismo tiempo";
	public static final String FIELD_DOCU_PRE="docuPreimpreso";
	public static final String FIELD_GEN_VALI="genValiCm";
	
    @Override
    public void initialize(GeneraValijaConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	ProductoDto productoDto = (ProductoDto) obj;
    	
    	Boolean docuPreimpreso= productoDto.getDocuPreimpreso()==null?false:productoDto.getDocuPreimpreso();
    	Boolean genValiCm= productoDto.getGenValiCm()==null?false:productoDto.getGenValiCm();
    	
    	if(docuPreimpreso) {
    		if(genValiCm!=null && genValiCm) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED, FIELD_DOCU_PRE);
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED, FIELD_GEN_VALI);
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
