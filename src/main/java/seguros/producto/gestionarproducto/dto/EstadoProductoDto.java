package seguros.producto.gestionarproducto.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class EstadoProductoDto {
	
	@Range(min = 0, max = 1, message="El campo habilitado solo puede ser 0 ó 1")
	@NotNull (message="El campo habilitado no puede ser nulo")
	private Long habilitado;
	
	@NotNull (message="La lista de id de productos no puede ser nula")
	private Set<Long> idsProducto = new HashSet<>();
	
}
