package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoPromocionDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoPromocionException;

public interface TipoPromocionService {
	
	public List<TipoPromocionDto> findAll() throws TipoPromocionException;
}
