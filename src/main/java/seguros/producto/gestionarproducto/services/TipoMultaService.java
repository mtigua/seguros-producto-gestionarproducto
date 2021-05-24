package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoMultaException;

public interface TipoMultaService {
	
	public List<TipoMultaDto> findAll() throws TipoMultaException;
}
