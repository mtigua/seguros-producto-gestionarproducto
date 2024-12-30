package seguros.producto.gestionarproducto.services;

import java.util.List;

import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoTramoException;

public interface TipoTramoService {
	
	public List<TipoTramoDto> findAll(Long tipoRamo) throws TipoTramoException;
}
