package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoService {
	
	public List<ProductoDto> findAll() throws ProductoException;
	public String save(ProductoDto producto) throws ProductoException;
}
