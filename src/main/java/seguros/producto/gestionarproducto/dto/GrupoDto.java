package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class GrupoDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre del grupo es requerido")
		private String nombre;

    
}
