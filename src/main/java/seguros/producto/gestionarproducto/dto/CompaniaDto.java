package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class CompaniaDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de la compania es requerido")
		private String nombre;

    
}
