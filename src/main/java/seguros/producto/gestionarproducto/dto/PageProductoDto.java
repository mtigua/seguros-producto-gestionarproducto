package seguros.producto.gestionarproducto.dto;

import java.util.List;
import org.springframework.data.domain.Page;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.Producto;

@Data
public class PageProductoDto {
	private int pageNumber;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private List<ProductoPageDto> productos;
	
	public PageProductoDto() {
		super();
	}
	
	public PageProductoDto( Page<Producto> page, List<ProductoPageDto> productos) {
		super();
		this.pageNumber = page.getNumber();
		this.pageSize = page.getSize();
		this.totalElements = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.productos= productos;
	}
	
	
}
