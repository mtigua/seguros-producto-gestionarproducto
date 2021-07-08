package seguros.producto.gestionarproducto.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProfesionDto {

	@NotNull(message = "El campo idProducto es requerido")
	private Long idProducto;
	@NotNull(message = "El campo idProfesion es requerido")
	private Long idProfesion;
	@NotNull(message = "El campo porcentaje es requerido")
	private Float porcentaje;
}
