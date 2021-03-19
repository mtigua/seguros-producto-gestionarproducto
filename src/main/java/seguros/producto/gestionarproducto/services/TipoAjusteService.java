package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoAjusteDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoAjusteException;

public interface TipoAjusteService {
	
	public List<TipoAjusteDto> findAll() throws TipoAjusteException;
}
