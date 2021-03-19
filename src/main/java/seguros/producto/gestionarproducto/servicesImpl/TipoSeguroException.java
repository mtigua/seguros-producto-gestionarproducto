package seguros.producto.gestionarproducto.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seguros.producto.gestionarproducto.exceptions.CommonException;

@Data
@EqualsAndHashCode(callSuper=false)
public class TipoSeguroException extends CommonException{

	private static final long serialVersionUID = 1L;
	
	public TipoSeguroException(Exception e) {
		super(e);
	}
	
	public TipoSeguroException() {
		super();
	}
}
