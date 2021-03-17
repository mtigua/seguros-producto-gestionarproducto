package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class GrupoMejorOfertaDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de la compania es requerido")
		private String nombre;

    
}
