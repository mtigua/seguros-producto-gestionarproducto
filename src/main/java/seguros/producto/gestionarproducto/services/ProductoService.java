package seguros.producto.gestionarproducto.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

public interface ProductoService {
	
	public List<ProductoDto> findAll() throws ProductoException;
	public String save(ProductoDto producto) throws ProductoException;
	public 	PageProductoDto findAllPaginated(Pageable pageable) throws ProductoException,PcbsException;
}
