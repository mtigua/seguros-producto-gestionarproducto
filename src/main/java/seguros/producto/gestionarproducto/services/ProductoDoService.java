package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.FormDataDescripcionOperativaSaveDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoDoException;

public interface ProductoDoService {
	
	public List<FormDataDescripcionOperativaSaveDto> findAll() throws ProductoDoException;
	
}
