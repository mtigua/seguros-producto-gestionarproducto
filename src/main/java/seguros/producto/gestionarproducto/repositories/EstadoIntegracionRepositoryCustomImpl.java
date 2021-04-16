package seguros.producto.gestionarproducto.repositories;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;

@Repository
public class EstadoIntegracionRepositoryCustomImpl implements EstadoIntegracionRepositoryCustom{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private PropertiesSql propertiesSql;	
	
	@Autowired 
	private Properties properties;
	

	






	
}
