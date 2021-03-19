package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TipoPeriodoDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoPeriodoException;

public interface TipoPeriodoService {
	
	public List<TipoPeriodoDto> findAll() throws TipoPeriodoException;
}
