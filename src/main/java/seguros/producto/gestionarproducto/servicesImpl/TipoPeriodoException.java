package seguros.producto.gestionarproducto.servicesImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seguros.producto.gestionarproducto.exceptions.CommonException;

@Data
@EqualsAndHashCode(callSuper=false)
public class TipoPeriodoException extends CommonException{

	private static final long serialVersionUID = 1L;
	
}
