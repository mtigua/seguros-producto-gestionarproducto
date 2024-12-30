package seguros.producto.gestionarproducto.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seguros.producto.gestionarproducto.exceptions.CommonException;

@Data
@EqualsAndHashCode(callSuper=false)
public class EstadoIntegracionException extends CommonException{

	private static final long serialVersionUID = 1L;
	
	public EstadoIntegracionException(Exception e) {
		super(e);
	}
	
	public EstadoIntegracionException() {
		super();
	}
	
	
}
