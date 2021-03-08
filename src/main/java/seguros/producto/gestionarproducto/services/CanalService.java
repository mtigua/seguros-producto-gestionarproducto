package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.CanalDto;
import seguros.producto.gestionarproducto.servicesImpl.CanalException;

public interface CanalService {
	
	public List<CanalDto> findAll() throws CanalException;
}
