package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoTraspasoDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoTraspasoException;

public interface TipoTraspasoService {
	
	public List<TipoTraspasoDto> findAll() throws TipoTraspasoException;
}
