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
public class ProductoRepositoryCustomImpl implements ProductoRepositoryCustom{

	
	private static final String MSG_NOT_RESULT= "No se ha retornado respuesta desde el proceso";
	private static final String PROCESS_NOT_FOUND="Proceso no identificado";
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private PropertiesSql propertiesSql;	
	
	@Autowired 
	private Properties properties;
	

	
	
//	@SuppressWarnings("unchecked")
//	@Transactional
//	@Override
//	public State BUSSINESS_RULES(Integer year, Integer month, String executor) throws ProductoException{
//		 String procedureName = propertiesSql.getBUSSINESS_RULES();	
//			
//		    List<Object[]> result=null;
//			MessageFlowTrack msgTrack_rules= new MessageFlowTrack();
//			String PREFIX_PARAMETER_YEAR_PLANES_U= properties.getPREFIX_PARAMETER_YEAR();
//			String PREFIX_PARAMETER_MONTH_PLANES_U= properties.getPREFIX_PARAMETER_MONTH();
//			String PREFIX_PARAMETER_CALLER_PLANES_U= properties.getPREFIX_PARAMETER_CALLER();
//			String currentDate=Utils.getCurrentDate();
//			
//			try {
//				msgTrack_rules.setProcess(properties.getPROCESS_NAME_BUSSINESS_RULE());
//				msgTrack_rules.setBeginDate(currentDate);
//				msgTrack_rules.setEndDate(currentDate);
//				msgTrack_rules.setYear(year);
//				msgTrack_rules.setMonth(month);
//				msgTrack_rules.setExecutor(executor);
//				
//				kafkaProducer.sendMessageTrack(properties.getTOPIC_FLOW_TRACK_CARGOABONO(),msgTrack_rules);
//				
//				StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);	
//				storedProcedureQuery.registerStoredProcedureParameter(PREFIX_PARAMETER_MONTH_PLANES_U, String.class, ParameterMode.IN);
//				storedProcedureQuery.registerStoredProcedureParameter(PREFIX_PARAMETER_YEAR_PLANES_U, String.class, ParameterMode.IN);
//				storedProcedureQuery.registerStoredProcedureParameter(PREFIX_PARAMETER_CALLER_PLANES_U, String.class, ParameterMode.IN);
//
//				storedProcedureQuery.setParameter(PREFIX_PARAMETER_MONTH_PLANES_U, String.valueOf(month));
//				storedProcedureQuery.setParameter(PREFIX_PARAMETER_YEAR_PLANES_U,  String.valueOf(year));
//				storedProcedureQuery.setParameter(PREFIX_PARAMETER_CALLER_PLANES_U, executor);
//
//				storedProcedureQuery.execute();
//				
//		        result = storedProcedureQuery.getResultList();
//				
//				if(result!=null) {
//					if(!result.isEmpty()) {
//						for(int i=0;i<result.size();i++) {
//							msgTrack_rules.setImpactedRecord(result.get(i)[7]!=null?Long.valueOf(result.get(i)[7].toString()):0L);
//							msgTrack_rules.setState(result.get(i)[9]!=null?State.valueOf(String.valueOf(result.get(i)[9].toString())):State.ERROR);	
//							msgTrack_rules.setObservation(result.get(i)[10]!=null?String.valueOf(result.get(i)[10].toString()):"");
//							Thread.sleep(3000);
//							msgTrack_rules.setEndDate(Utils.getCurrentDate());
//							msgTrack_rules.setProcess(result.get(i)[1]!=null?String.valueOf(result.get(i)[1].toString()):PROCESS_NOT_FOUND);
//							kafkaProducer.sendMessageTrack(properties.getTOPIC_FLOW_TRACK_CARGOABONO(),msgTrack_rules);
//						}						
//					}
//				}
//				else {
//					msgTrack_rules.setState(State.EXITOSO);
//					msgTrack_rules.setObservation(MSG_NOT_RESULT);	
//				}
//				
//			}
//			catch(Exception e) {			
//				msgTrack_rules.setState(State.ERROR);	
//				msgTrack_rules.setObservation(e.toString());			
//
//				ProductoException exc = new ProductoException();
//				exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
//				exc.setDetail(e.getCause()!=null?e.getCause().toString():e.getMessage());
//				exc.setConcreteException(e);
//				throw exc;			
//			}
//			finally {
//				msgTrack_rules.setEndDate(Utils.getCurrentDate());
//				kafkaProducer.sendMessageTrack(properties.getTOPIC_FLOW_TRACK_CARGOABONO(),msgTrack_rules);
//			}
//			return msgTrack_rules.getState();
//	}







	
}
