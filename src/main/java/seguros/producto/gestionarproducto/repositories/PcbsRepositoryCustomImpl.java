package seguros.producto.gestionarproducto.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

@Repository
public class PcbsRepositoryCustomImpl implements PCBSRepositoryCustom{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private PropertiesSql propertiesSql;	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MonedaDto> findAllMoneda() throws PcbsException {
		
			String procedureName = propertiesSql.getLISTAR_MONEDAS();
			List<MonedaDto> list =  new ArrayList<>();
			List<Object[]> record=null;
			 
			try {
				StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);	      
				storedProcedureQuery.execute();
				record = storedProcedureQuery.getResultList();
			
				if(record!=null) {
					record.stream().forEach(p -> {
						MonedaDto monedaDto =  new MonedaDto();
						monedaDto.setId(p[0].toString());
						monedaDto.setNombre(p[1].toString());
						list.add(monedaDto); 
					});
				}
			}
			catch(Exception e) {
				PcbsException exc = new PcbsException();
				exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
				exc.setDetail(procedureName + " : " + e.getMessage());
				exc.setConcreteException(e);
				throw exc;
			}

			return list;
		
	}
	

	






	
}
