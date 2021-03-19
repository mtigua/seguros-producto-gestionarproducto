package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoSeguroException;

public interface TipoSeguroService {
	
	public List<TipoSeguroDto> findAll() throws TipoSeguroException;
}
