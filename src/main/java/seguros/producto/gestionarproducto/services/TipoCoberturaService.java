package seguros.producto.gestionarproducto.services;

import seguros.producto.gestionarproducto.dto.TipoCoberturaDto;
import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.entities.TipoCobertura;
import seguros.producto.gestionarproducto.servicesImpl.TipoCoberturaException;
import seguros.producto.gestionarproducto.servicesImpl.TipoSeguroException;

import java.util.List;

public interface TipoCoberturaService {
	
	public List<TipoCoberturaDto> findAll() throws TipoCoberturaException;
}
