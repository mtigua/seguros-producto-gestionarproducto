package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class EquivalenciaSeguroDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de la equivalencia es requerido")
		private String nombre;

    
}
