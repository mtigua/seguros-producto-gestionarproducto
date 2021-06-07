package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TipoRamoDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre es requerido")
	private String nombre;
	
	private String descripcion;	
	
    
}
