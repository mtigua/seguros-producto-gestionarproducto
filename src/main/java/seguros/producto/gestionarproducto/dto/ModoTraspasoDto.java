package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModoTraspasoDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre de modo de traspaso es requerido")
	private String nombre;
	
	private String descripcion;

	public ModoTraspasoDto(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public ModoTraspasoDto() {
		super();
	}	
	
    
}
