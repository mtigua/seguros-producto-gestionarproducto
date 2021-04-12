package seguros.producto.gestionarproducto.repositories;

import seguros.producto.gestionarproducto.dto.ProductoPageDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;
import java.util.List;

public interface ProductoRepositoryCustom {
	
	
	
	public 	List<ProductoPageDto> findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException,PcbsException;

	
}
