package seguros.producto.gestionarproducto.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seguros.producto.gestionarproducto.exceptions.CommonException;

@Data
@EqualsAndHashCode(callSuper=false)
public class TipoPromocionException extends CommonException{

	private static final long serialVersionUID = 1L;
	

	public TipoPromocionException(Exception e) {
		super(e);
	}
	
	public TipoPromocionException() {
		super();
	}
}
