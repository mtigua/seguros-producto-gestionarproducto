package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TipoMultaDto  {
	
    private Long id;
	
	@NotBlank(message = "El campo nombre de tipo de multa es requerido")
	private String nombre;
	
	private String descripcion;	
	
    
}
