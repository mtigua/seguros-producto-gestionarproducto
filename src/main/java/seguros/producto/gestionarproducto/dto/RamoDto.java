package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class RamoDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre del ramo es requerido")
		private String nombre;

    
}
