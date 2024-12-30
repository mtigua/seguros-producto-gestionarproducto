package seguros.producto.gestionarproducto.services;

import java.util.List;
import seguros.producto.gestionarproducto.dto.DestinoVentaDto;
import seguros.producto.gestionarproducto.servicesImpl.DestinoVentaException;

public interface DestinoVentaService {
	
	public List<DestinoVentaDto> findAll() throws DestinoVentaException;
}
