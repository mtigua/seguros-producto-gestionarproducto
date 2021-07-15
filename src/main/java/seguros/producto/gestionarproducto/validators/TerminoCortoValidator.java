package seguros.producto.gestionarproducto.validators;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;

public class TerminoCortoValidator implements ConstraintValidator<TerminoCortoConstraint, Object> {
	

	public static final String MENSAJE_ERROR_REQUIRED_PRIMA = "El campo primaPeriodo (Prima período) es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MONTO = "El campo monto (Monto) es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_CUOTA = "El campo cuotas (Cuotas) es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_MONEDA = "El campo moneda (Moneda) es requerido";
	public static final String MENSAJE_ERROR_REQUIRED_TIPO_MULTA = "El campo moneda (Tipo de multa) es requerido";
	
	
	public static final String MENSAJE_ERROR_NOT_REQUIRED_PRIMA = "El campo primaPeriodo (Acreedor) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_MONTO = "El campo monto (Acreedor) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_CUOTA = "El campo cuotas (Acreedor) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_MONEDA = "El campo moneda (Acreedor) es requerido";
	
	
	public static final String fieldNameTipoMulta="tipoMulta";
	public static final String fieldNameMesDesde="mesDesde";
	public static final String fieldNameMesHasta="mesHasta";
	public static final String fieldNamePrimaPeriodo="primaPeriodo";
	public static final String fieldNameMonto="monto";
	public static final String fieldNameCuotas="cuotas";
	public static final String fieldNameMoneda="moneda";
   
	
	@Override
    public void initialize(TerminoCortoConstraint acreedor) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	TerminoCortoDto terminoCortoDto = (TerminoCortoDto) obj;
    	
    	
    	TipoMultaDto tipoMulta= terminoCortoDto.getTipoMulta()==null?new TipoMultaDto(0L,"",""):terminoCortoDto.getTipoMulta();
    	BigDecimal primaPeriodo= terminoCortoDto.getPrimaPeriodo()==null?new BigDecimal(0):terminoCortoDto.getPrimaPeriodo();    		
    	BigDecimal monto= terminoCortoDto.getMonto()==null?new BigDecimal(0): terminoCortoDto.getMonto();    		
        Integer cuotas= terminoCortoDto.getCuotas()==null?0: terminoCortoDto.getCuotas();    		
    	String moneda= terminoCortoDto.getMoneda()==null?"":terminoCortoDto.getMoneda();    	
    	
    	
    	if(tipoMulta.getId() == 1L) {
    		if(moneda.trim().isEmpty()) {
    			 customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MONEDA,fieldNameMoneda); 
      			return false; 
    		}
    		if(monto.signum()<=0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MONTO,fieldNameMonto); 
       			return false;
    		}
    		if(monto.signum()<=0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MONTO,fieldNameMonto); 
       			return false;
    		}
    		if(monto.signum()<=0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MONTO,fieldNameMonto); 
       			return false;
    		}
    	}
    	else if(tipoMulta.getId() == 2L){    	
    		if(primaPeriodo.signum()<=0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_PRIMA,fieldNamePrimaPeriodo); 
       			return false;
    		}    			   		
    	}
    	else if(tipoMulta.getId() == 3L){    	
    		if(cuotas <=0) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_CUOTA,fieldNameCuotas); 
       			return false;
    		}    			   		
    	}
    	else {
    		customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_TIPO_MULTA,fieldNameTipoMulta); 
   			return false;
    	}
        
    	return true;
    }
    
    private void customMessageForValidation(ConstraintValidatorContext cxt, String message, String field) {
    	cxt.disableDefaultConstraintViolation();
    	cxt.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }
    
}
