package seguros.producto.gestionarproducto.dto;


import java.util.List;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CanalDto  {
	
	
	private Long id;
	
	@NotBlank(message = "El campo nombre de canal es requerido")
	private String nombre;
	
	private String descripcion;	
	
	private List<DatoComplementarioDto> datosComplentarios;

	public CanalDto(Long id,String nombre,String descripcion, List<DatoComplementarioDto> datosComplentarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.datosComplentarios = datosComplentarios;
	}

	public CanalDto() {
		super();
	}
    
	
}
