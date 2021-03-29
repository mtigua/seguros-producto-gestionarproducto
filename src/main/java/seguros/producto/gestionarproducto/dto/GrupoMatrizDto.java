package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class GrupoMatrizDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre del grupo matriz es requerido")
		private String nombre;

    
}
