package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.ConceptoDto;
import seguros.producto.gestionarproducto.servicesImpl.ConceptoException;

public interface ConceptoService {
	
	public List<ConceptoDto> findAll() throws ConceptoException;
}
