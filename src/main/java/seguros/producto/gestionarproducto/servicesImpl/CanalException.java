package seguros.producto.gestionarproducto.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seguros.producto.gestionarproducto.exceptions.CommonException;

@Data
@EqualsAndHashCode(callSuper=false)
public class CanalException extends CommonException{

	private static final long serialVersionUID = 1L;
	

	public CanalException(Exception e) {
		super(e);
	}
	
	public CanalException() {
		super();
	}
	
	
}
