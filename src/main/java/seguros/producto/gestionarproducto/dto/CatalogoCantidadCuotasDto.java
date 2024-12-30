package seguros.producto.gestionarproducto.dto;

import lombok.Data;

@Data
public class CatalogoCantidadCuotasDto {
	private Long id;
	private String name;

	public CatalogoCantidadCuotasDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
