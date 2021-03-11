package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class NegocioDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre del negocio es requerido")
		private String nombre;

    
}
