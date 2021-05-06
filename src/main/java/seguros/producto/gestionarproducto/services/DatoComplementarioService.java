package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.DatoComplementarioDto;
import seguros.producto.gestionarproducto.servicesImpl.DatoComplementarioException;

public interface DatoComplementarioService {
	
	public List<DatoComplementarioDto> findAll() throws DatoComplementarioException;
}
