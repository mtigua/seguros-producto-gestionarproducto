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
	
	
	public CommonException(Exception e) {
		this.concreteException = e;
		if(e.getCause()!=null) {
			if(e.getCause().getCause() != null) {
				this.detail = e.getCause().getCause().getLocalizedMessage();				
				this.errorMessage=e.getClass().toString() + " " +e.getCause().getCause().getLocalizedMessage();				
			}
			else {
				this.detail = e.getMessage();				
				this.errorMessage=e.getClass().toString() + " " + e.getMessage();
			}
		}	
	}

	public CommonException() {
		super();
	}
	
	

}
