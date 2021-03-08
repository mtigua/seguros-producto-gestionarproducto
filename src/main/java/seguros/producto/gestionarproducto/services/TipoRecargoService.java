package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoRecargoDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoRecargoException;

public interface TipoRecargoService {
	
	public List<TipoRecargoDto> findAll() throws TipoRecargoException;
}
