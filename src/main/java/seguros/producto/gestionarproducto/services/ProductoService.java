package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoPageDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoService {
	
	public List<ProductoDto> findAll() throws ProductoException;
	public String save(ProductoDto producto) throws ProductoException;
	public 	List<ProductoPageDto> findAllPaginated( int page, int size, Integer idCompania, Integer idNegocio, Integer idRamo, String nemotecnico, String descripcion) throws ProductoException,PcbsException;
}
