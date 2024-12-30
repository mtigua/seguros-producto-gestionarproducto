package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.HomologacionIdentificadorDto;
import seguros.producto.gestionarproducto.servicesImpl.HomologacionIdentificadorException;

public interface HomologacionIdentificadorService {
	
	public List<HomologacionIdentificadorDto> findAll() throws HomologacionIdentificadorException;
}
