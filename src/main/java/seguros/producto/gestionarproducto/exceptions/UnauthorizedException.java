package seguros.producto.gestionarproducto.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UnauthorizedException extends RuntimeException {

	
	
	private static final long serialVersionUID = -4163208132552471435L;
	private String subject;
	private String detail;
	private Exception concreteException;
	private String errorMessage;

}
