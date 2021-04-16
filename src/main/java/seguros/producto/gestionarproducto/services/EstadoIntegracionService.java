package seguros.producto.gestionarproducto.services;


import seguros.producto.gestionarproducto.entities.EstadoIntegracion;
import seguros.producto.gestionarproducto.servicesImpl.EstadoIntegracionException;

public interface EstadoIntegracionService {
	
	public void save(EstadoIntegracion estadoIntegracion) throws EstadoIntegracionException;
}
