package seguros.producto.gestionarproducto.repositories;

import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoRepositoryCustom {
	
	
	
	public 	PageProductoDto findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException,PcbsException;

	
}
