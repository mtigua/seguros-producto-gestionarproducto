package seguros.producto.gestionarproducto.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seguros.producto.gestionarproducto.exceptions.CommonException;

@Data
@EqualsAndHashCode(callSuper=false)
public class ParentescoException extends CommonException{

	private static final long serialVersionUID = 1L;

	public ParentescoException(Exception e) {
		super(e);
	}

	public ParentescoException() {
		super();
	}
}
