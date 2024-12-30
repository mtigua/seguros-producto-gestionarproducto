package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.servicesImpl.TarifaEsException;

public interface TarifaEsService {
	
	public List<TarifaEsDto> findAll() throws TarifaEsException;
}
