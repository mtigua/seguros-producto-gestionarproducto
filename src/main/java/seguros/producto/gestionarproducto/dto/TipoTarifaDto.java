package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class TipoTarifaDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de tipo de tarifa es requerido")
		private String nombre;
		
		private String descripcion;	
	
    
}
