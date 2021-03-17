package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class SubtipoDto  {
	
	    private String id;
		
		@NotBlank(message = "El campo nombre del subtipo es requerido")
		private String nombre;

    
}
