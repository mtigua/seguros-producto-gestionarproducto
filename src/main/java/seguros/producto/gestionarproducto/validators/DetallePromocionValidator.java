package seguros.producto.gestionarproducto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import seguros.producto.gestionarproducto.dto.DetallePromocionDto;

public class DetallePromocionValidator implements ConstraintValidator<DetallePromocionConstraint, Object> {
	
	public static final int ID_TIPO_PROMOCION_GIFTCARD = 1;
	public static final int ID_TIPO_PROMOCION_PUNTOSNECTAR = 2;
	public static final int ID_TIPO_PROMOCION_PRODUCTO = 3;
	
	public static final String MENSAJE_ERROR_VALOR_GIFTCARD = "El monto del gift card debe ser mayor a cero";
	public static final String MENSAJE_ERROR_NOT_VALID= "El campo cuotasHasta debe ser mayor que el campo cuotasDesde";
	public static final String MENSAJE_ERROR_REQUIRED_ID_PROMOCION_PRODUCTO= "El campo idPromocionProducto (Producto en promocion) es requerido";
	
	public static final String fieldNameMontoGiftCard= "montoGiftCard";
	public static final String fieldNameCuotasHasta = "cuotasHasta";
	public static final String fieldNameIdPromocionProducto = "idPromocionProducto";
	
	
    @Override
    public void initialize(DetallePromocionConstraint adjunto) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cxt) {
    	DetallePromocionDto detallePromocionDto = (DetallePromocionDto) obj;
    	switch (detallePromocionDto.getIdTipoPromocion().intValue()) {
	    	case ID_TIPO_PROMOCION_GIFTCARD:
	    		if (detallePromocionDto.getMontoGiftCard()<=0) {
	    			customMessageForValidation(cxt,MENSAJE_ERROR_VALOR_GIFTCARD,fieldNameMontoGiftCard); 
	    			return false;
	    		}
	    		break;
	    	case ID_TIPO_PROMOCION_PUNTOSNECTAR:
	    		Integer cuotasDesde = detallePromocionDto.getCuotasDesde();
	    		Integer cuotasHasta =  detallePromocionDto.getCuotasHasta();
	    		if( cuotasHasta < cuotasDesde) {
	    			customMessageForValidation(cxt,MENSAJE_ERROR_NOT_VALID,fieldNameCuotasHasta); 
	    			return false;
	    		}	
	    		break;
	    	case ID_TIPO_PROMOCION_PRODUCTO:
	    		if (detallePromocionDto.getIdPromocionProducto()  <= 0L) {
    				customMessageForValidation(cxt,MENSAJE_ERROR_REQUIRED_ID_PROMOCION_PRODUCTO,fieldNameIdPromocionProducto); 
        			return false;
	    		}
	    		break;	
		}
    	return true;
    }
    
    private void customMessageForValidation(ConstraintValidatorContext cxt, String message,String field) {
    	cxt.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
    }
    
}
