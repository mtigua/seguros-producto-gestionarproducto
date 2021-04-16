package seguros.producto.gestionarproducto.servicesImpl;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.entities.EstadoIntegracion;
import seguros.producto.gestionarproducto.repositories.EstadoIntegracionRepository;
import seguros.producto.gestionarproducto.services.EstadoIntegracionService;


@Service
public class EstadoIntegracionServiceImpl implements EstadoIntegracionService {
	

	
	@Autowired
	private EstadoIntegracionRepository estadoIntegracionRepository;
	
	

	@Transactional
	@Override
	public void save(EstadoIntegracion estadoIntegracion) throws EstadoIntegracionException {
		
		try {
			estadoIntegracionRepository.save(estadoIntegracion);
				 
		}
		catch(Exception e) {
			EstadoIntegracionException exc = new EstadoIntegracionException(e);
			throw exc;
		}
		
	}
	
	

	

	
	
	


	
}
