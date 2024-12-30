package seguros.producto.gestionarproducto.dto;

import java.util.List;
import lombok.Data;

@Data
public class PageProductoDto {
	
	private Integer totalElements;
	private List<ProductoPageDto> productos;
	
	public PageProductoDto() {
		super();
	}
	
	public PageProductoDto(List<ProductoPageDto> productos, Integer totalElements) {
		super();
		this.totalElements = totalElements;
		this.productos= productos;
	}
	
	
}
