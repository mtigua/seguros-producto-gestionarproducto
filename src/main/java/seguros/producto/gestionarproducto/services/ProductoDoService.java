package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.FormDataDescripcionOperativaDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoDoException;

public interface ProductoDoService {
	
	public List<FormDataDescripcionOperativaDto> findAll() throws ProductoDoException;
	
}
