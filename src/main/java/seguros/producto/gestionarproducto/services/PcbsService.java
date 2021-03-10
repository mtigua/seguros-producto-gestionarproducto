package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

public interface PcbsService {
	
	public List<MonedaDto> findAllMonedas() throws PcbsException;
	
}
