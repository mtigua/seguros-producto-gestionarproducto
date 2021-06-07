package seguros.producto.gestionarproducto.validators;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import seguros.producto.gestionarproducto.dto.TramoDto;

public class TramoValidator implements ConstraintValidator<TramoConstraint, Object> {
	
	
	public static final String MENSAJE_ERROR_NOT_VALID= "El campo valorHasta (Monto hasta/ Edad hasta) debe ser mayor que el campo valorDesde (Monto desde/ Edad desde)  ";
	public static final String MENSAJE_ERROR_REQUIRED_MONEDA= "El campo moneda es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_MONEDA= "El campo moneda debe ser vac\u00EDo o null";
	public static final String MENSAJE_ERROR_REQUIRED_TIPO_TASA= "El campo tipoTasa (Expresado en) es requerido";
	public static final String MENSAJE_ERROR_NOT_REQUIRED_TIPO_TASA= "El campo tipoTasa (Expresado en) debe ser 0 o null";
	public static final String MENSAJE_ERROR_NOT_INTEGER_EDAD_DESDE= "El campo valorDesde (Edad desde) debe ser un valor entero";
	public static final String MENSAJE_ERROR_NOT_INTEGER_EDAD_HASTA= "El campo valorHasta (Edad hasta) debe ser un valor entero";

	public static final String fieldNameValorDesde= "valorDesde";
	public static final String fieldNameValorHasta= "valorHasta";
	public static final String fieldNameMoneda= "moneda";
	public static final String fieldNameTipoTasa= "tipoTasa";

	
    @Override
    public void initialize(TramoConstraint tramo) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	TramoDto tramoDto = (TramoDto) obj;
    
    	
    	BigDecimal valorDesde= tramoDto.getValorDesde()==null?new BigDecimal(0):tramoDto.getValorDesde();
    	BigDecimal valorHasta= tramoDto.getValorHasta()==null?new BigDecimal(0):tramoDto.getValorHasta();
    	Long tarifaEs= tramoDto.getTarifaEs()==null?0L:tramoDto.getTarifaEs();
    	String moneda = tramoDto.getMoneda()==null?"":tramoDto.getMoneda().trim();
    	Long tipoTasa = tramoDto.getTipoTasa()==null?0L:tramoDto.getTipoTasa();
    	Long tipoTramo = tramoDto.getTipoTramo()==null?0L:tramoDto.getTipoTramo();
    
    	
    	
            if(tipoTramo == 2L) {            	
	             try {
	            	 valorDesde.toBigIntegerExact();
	            	    
	               } catch (ArithmeticException ex) {
	                 	customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_EDAD_DESDE,fieldNameValorDesde); 
	            		return false;
	               }
	             
	             try {
	            	 valorHasta.toBigIntegerExact();
	            	    
	               } catch (ArithmeticException ex) {
	                 	customMessageForValidation(cxt,MENSAJE_ERROR_NOT_INTEGER_EDAD_DESDE,fieldNameValorDesde); 
	            		return false;
	               }            	
            }
            
    		if(valorHasta.compareTo(valorDesde) < 0 ) {
    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_VALID,fieldNameValorHasta); 
    			return false;
    		}	
    		
    		if(tarifaEs==1L) {
    			if(moneda.isEmpty()) {
    				customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_MONEDA,fieldNameMoneda); 
        			return false;
    			}
    		}
    		else {
    			if(!moneda.isEmpty()) {
    				customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED_MONEDA,fieldNameMoneda); 
        			return false;
    			}
    		}
    		
    		if(tarifaEs==2L) {
    			if(tipoTasa <= 0L) {
    				customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_TIPO_TASA,fieldNameTipoTasa); 
        			return false;
    			}
    		}
    		else {
    			if(tipoTasa > 0L) {
    				customMessageForValidation(cxt,MENSAJE_ERROR_NOT_REQUIRED_TIPO_TASA,fieldNameTipoTasa); 
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
