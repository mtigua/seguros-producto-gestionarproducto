package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoDescuentoDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoDescuentoException;

public interface TipoDescuentoService {
	
	public List<TipoDescuentoDto> findAll() throws TipoDescuentoException;
}
