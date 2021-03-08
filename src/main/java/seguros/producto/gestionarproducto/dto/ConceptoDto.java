package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ConceptoDto  {
	
    private Long id;
	
	@NotBlank(message = "El campo nombre de concepto es requerido")
	private String nombre;
	
	private String descripcion;	
	
    
}
