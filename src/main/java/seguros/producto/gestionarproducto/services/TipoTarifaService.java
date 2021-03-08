package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoTarifaDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoTarifaException;

public interface TipoTarifaService {
	
	public List<TipoTarifaDto> findAll() throws TipoTarifaException;
}
