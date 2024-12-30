package seguros.producto.gestionarproducto.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9079454849611061074L;
	
	private String subject;
	private String detail;
	private Exception concreteException;
	private String errorMessage;

}
