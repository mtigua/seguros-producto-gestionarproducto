package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.ProductoDoDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoDoException;

public interface ProductoDoService {
	
	public List<ProductoDoDto> findAll() throws ProductoDoException;
	
}
