package seguros.producto.gestionarproducto.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;
import seguros.producto.gestionarproducto.utils.Utils;

@Repository
public class ProductoDoRepositoryCustomImpl implements ProductoDoRepositoryCustom{

	
	private static final String MSG_NOT_RESULT= "No se ha retornado respuesta desde el proceso";
	private static final String PROCESS_NOT_FOUND="Proceso no identificado";
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private PropertiesSql propertiesSql;	
	
	@Autowired 
	private Properties properties;
	

	






	
}
