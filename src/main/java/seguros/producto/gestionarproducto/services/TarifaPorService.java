package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.TarifaPorDto;
import seguros.producto.gestionarproducto.servicesImpl.TarifaPorException;

public interface TarifaPorService {
	
	public List<TarifaPorDto> findAll() throws TarifaPorException;
}
