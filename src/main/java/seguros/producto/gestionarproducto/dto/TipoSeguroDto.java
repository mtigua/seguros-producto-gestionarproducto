package seguros.producto.gestionarproducto.dto;



import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TipoSeguroDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de tipo de seguro es requerido")
		private String nombre;
		
		private String descripcion;	
	
    
}
