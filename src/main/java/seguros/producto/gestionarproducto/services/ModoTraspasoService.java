package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.ModoTraspasoDto;
import seguros.producto.gestionarproducto.servicesImpl.ModoTraspasoException;

public interface ModoTraspasoService {
	
	public List<ModoTraspasoDto> findAll() throws ModoTraspasoException;
}
