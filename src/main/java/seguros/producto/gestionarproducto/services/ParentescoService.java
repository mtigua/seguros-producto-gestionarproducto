package seguros.producto.gestionarproducto.services;

import seguros.producto.gestionarproducto.dto.ParentescoDto;
import seguros.producto.gestionarproducto.servicesImpl.ParentescoException;

import java.util.List;

public interface ParentescoService {
	
	public List<ParentescoDto> findAll() throws ParentescoException;
}
