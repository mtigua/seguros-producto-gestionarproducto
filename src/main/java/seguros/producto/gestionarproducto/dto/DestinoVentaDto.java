package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DestinoVentaDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre de destino es requerido")
	private String nombre;
	
	private String descripcion;	
	
    
}
