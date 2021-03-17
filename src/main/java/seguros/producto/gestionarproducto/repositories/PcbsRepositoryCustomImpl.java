package seguros.producto.gestionarproducto.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

@Repository
public class PcbsRepositoryCustomImpl implements PCBSRepositoryCustom{
	
	private static final String VALUE_UNDEFINED="No se ha podido indentificar valor para el par\u00E1metro de salida: ";

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
				PcbsException exc = new PcbsException(e);
				throw exc;
			}

			return list;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompaniaDto> findAllCompania() throws PcbsException {
		String procedureNameCompania = propertiesSql.getLISTAR_COMPANIAS();
		List<CompaniaDto> listCompania =  new ArrayList<>();
		List<Object[]> recordCompania=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameCompania);	      
			storedProcedureQuery.execute();
			recordCompania = storedProcedureQuery.getResultList();
		
			if(recordCompania!=null) {
				recordCompania.stream().forEach(p -> {
					CompaniaDto companiaDto =  new CompaniaDto();
					companiaDto.setId(Long.valueOf((p[0]).toString()));
					companiaDto.setNombre(p[1].toString());
					listCompania.add(companiaDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}

		return listCompania;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NegocioDto> findAllNegocioByCompania(Long idCompania) throws PcbsException {
		String procedureNameNegocio = propertiesSql.getLISTAR_NEGOCIOS_POR_COMPANIA();
		List<NegocioDto> listNegocio =  new ArrayList<>();
		List<Object[]> recordNegocio=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameNegocio);
			storedProcedureQuery.registerStoredProcedureParameter("idCompania", Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("idCompania",idCompania );
			storedProcedureQuery.execute();
			recordNegocio = storedProcedureQuery.getResultList();
		
			if(recordNegocio!=null) {
				recordNegocio.stream().forEach(p -> {
					NegocioDto negocioDto =  new NegocioDto();
					negocioDto.setId(Long.valueOf((p[0]).toString()));
					negocioDto.setNombre(p[1].toString());
					listNegocio.add(negocioDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}

		return listNegocio;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RamoDto> findAllRamoByCompaniaNegocio(Long idCompania, Long idNegocio) throws PcbsException {
		String procedureNameRamo = propertiesSql.getLISTAR_RAMOS_POR_COMPANIA_NEGOCIO();
		List<RamoDto> listRamo =  new ArrayList<>();
		List<Object[]> recordRamo=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameRamo);
			storedProcedureQuery.registerStoredProcedureParameter("idCompania", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("idNegocio", Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("idCompania",idCompania );
			storedProcedureQuery.setParameter("idNegocio",idNegocio );
			storedProcedureQuery.execute();
			recordRamo = storedProcedureQuery.getResultList();
		
			if(recordRamo!=null) {
				recordRamo.stream().forEach(p -> {
					RamoDto ramoDto =  new RamoDto();
					ramoDto.setId(Long.valueOf((p[0]).toString()));
					ramoDto.setNombre(p[1].toString());
					listRamo.add(ramoDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}

		return listRamo;
	}

	
	@Override
	@Transactional
	public Integer findNumPoliza(String numPoliza) throws PcbsException {
		String procedureBuscaPoliza = propertiesSql.getBUSCAR_POLIZA();
		Integer existe=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaPoliza);
			storedProcedureQuery.registerStoredProcedureParameter("numPoliza", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("existe", Integer.class, ParameterMode.OUT);
			
			storedProcedureQuery.setParameter("numPoliza",numPoliza );
			storedProcedureQuery.execute();
		
			Object result= storedProcedureQuery.getOutputParameterValue("existe");
			  if(result!=null) {
				  existe= (int) storedProcedureQuery.getOutputParameterValue("existe");
			  }
			  else {
				    PcbsException exc = new PcbsException();
					exc.setErrorMessage(VALUE_UNDEFINED + "existe");	        	
					exc.setDetail(VALUE_UNDEFINED + "existe");
					exc.setConcreteException(exc);
					throw exc;
			  }		
		
		}
		catch(PcbsException e){
			throw e;
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}

		return existe;
	}

	

	

	






	
}
