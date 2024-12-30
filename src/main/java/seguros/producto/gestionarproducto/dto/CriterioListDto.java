package seguros.producto.gestionarproducto.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CriterioListDto {

	private Long id;
	private String nombre;
	private Boolean seleccionado;
}
