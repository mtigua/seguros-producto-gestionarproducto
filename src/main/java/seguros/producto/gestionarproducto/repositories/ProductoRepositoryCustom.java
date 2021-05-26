package seguros.producto.gestionarproducto.repositories;

import seguros.producto.gestionarproducto.dto.CoberturaProductoDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

import java.util.List;

public interface ProductoRepositoryCustom {
	
	
	
	public 	PageProductoDto findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException;

	public InfoProductoDto getInfoProducto(Long id) throws ProductoException;

	public List<CoberturaProductoDto> findCoberturasDtoByProducto(Long id) throws ProductoException;
	
	public String generateNemotecnico() throws ProductoException;
}
