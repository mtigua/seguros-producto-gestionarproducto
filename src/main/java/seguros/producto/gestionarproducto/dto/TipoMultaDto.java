package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TipoMultaDto  {
	
    private Long id;
	
	@NotBlank(message = "El campo nombre de tipo de multa es requerido")
	private String nombre;
	
	private String descripcion;

	public TipoMultaDto(Long id,  String nombre,
			String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public TipoMultaDto() {
		super();
	}	
	
    
}
