package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TarifaEsDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre de canal es requerido")
	private String nombre;
	
	private String descripcion;

	public TarifaEsDto(Long id,String nombre,String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public TarifaEsDto() {
		super();
	}	
	
	
	
    
}
