package seguros.producto.gestionarproducto.repositories;

import java.util.List;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;


public interface PCBSRepositoryCustom {
	
	public List<MonedaDto> findAllMoneda() throws PcbsException;
	

	
}
