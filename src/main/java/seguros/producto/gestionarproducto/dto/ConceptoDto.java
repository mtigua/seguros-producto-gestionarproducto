package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ConceptoDto  {
	
    private Long id;
	
	@NotBlank(message = "El campo nombre de concepto es requerido")
	private String nombre;
	
	private String descripcion;

	public ConceptoDto(Long id, String nombre,
			String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public ConceptoDto() {
		super();
	}	
	
    
}
