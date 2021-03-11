package seguros.producto.gestionarproducto.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommonException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String subject;
	private String detail;
	private Exception concreteException;
	private String errorMessage;

}
