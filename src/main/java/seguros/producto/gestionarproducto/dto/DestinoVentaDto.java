package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DestinoVentaDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre de destino es requerido")
	private String nombre;
	
	private String descripcion;

	public DestinoVentaDto(Long id, String nombre,
			String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public DestinoVentaDto() {
		super();
	}	
	
	
    
}
