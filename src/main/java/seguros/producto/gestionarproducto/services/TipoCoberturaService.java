package seguros.producto.gestionarproducto.services;

import seguros.producto.gestionarproducto.dto.TipoCoberturaDto;
import seguros.producto.gestionarproducto.servicesImpl.TipoCoberturaException;

import java.util.List;

public interface TipoCoberturaService {
	
	public List<TipoCoberturaDto> findAll() throws TipoCoberturaException;
}
