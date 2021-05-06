package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DatoComplementarioDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre de canal es requerido")
	private String nombre;
	
	private String descripcion;	
	
	@NotBlank(message = "El campo url es requerido")
	private String url;
	
	@NotBlank(message = "El campo expresion es requerido")
	private String expression;
    
}
