package seguros.producto.gestionarproducto.services;

import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoTasaException;

import java.util.List;

public interface TipoTasaService {
	
	public List<TipoTasaDto> findAll() throws TipoTasaException;
}
