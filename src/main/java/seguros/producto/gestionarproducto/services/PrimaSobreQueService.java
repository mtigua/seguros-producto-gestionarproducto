package seguros.producto.gestionarproducto.services;

import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.servicesImpl.PrimaSobreQueException;

import java.util.List;

public interface PrimaSobreQueService {
	
	public List<PrimaSobreQueDto> findAll() throws PrimaSobreQueException;
}
