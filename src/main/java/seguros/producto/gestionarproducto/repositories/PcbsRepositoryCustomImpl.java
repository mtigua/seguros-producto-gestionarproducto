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
import seguros.producto.gestionarproducto.dto.EquivalenciaSeguroDto;
import seguros.producto.gestionarproducto.dto.GrupoDto;
import seguros.producto.gestionarproducto.dto.GrupoMatrizDto;
import seguros.producto.gestionarproducto.dto.GrupoMejorOfertaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.ProdDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.dto.SubtipoDto;
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
	@SuppressWarnings("unchecked")
	public List<SubtipoDto> findAllSubtipoByCompaniaRamo(Long idCompania,Long idRamo) throws PcbsException {
		String procedureNameSubtipo = propertiesSql.getLISTAR_SUBTIPOS_POR_COMPANIA_RAMO();
		List<SubtipoDto> listSubtipo =  new ArrayList<>();
		List<Object[]> recordSubtipo=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameSubtipo);
			storedProcedureQuery.registerStoredProcedureParameter("idCompania", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("idRamo", Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("idCompania",idCompania);
			storedProcedureQuery.setParameter("idRamo",idRamo);
			
			storedProcedureQuery.execute();
			recordSubtipo = storedProcedureQuery.getResultList();
		
			if(recordSubtipo!=null) {
				recordSubtipo.stream().forEach(p -> {
					SubtipoDto subtipoDto =  new SubtipoDto();
					subtipoDto.setId((p[0]).toString());
					subtipoDto.setNombre(p[1].toString());
					listSubtipo.add(subtipoDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
			exc.setDetail(procedureNameSubtipo + " : " + e.getMessage());
			exc.setConcreteException(e);
			throw exc;
		}

		return listSubtipo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProdDto> findAllProductoBySubtipo(String codigoSubTipo) throws PcbsException {
		String procedureNameProducto = propertiesSql.getLISTAR_PRODUCTOS_POR_SUBTIPO();
		List<ProdDto> listProducto =  new ArrayList<>();
		List<Object[]> recordProducto=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameProducto);
			storedProcedureQuery.registerStoredProcedureParameter("codigoSubTipo", String.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("codigoSubTipo",codigoSubTipo);
			storedProcedureQuery.execute();
			recordProducto = storedProcedureQuery.getResultList();
		
			if(recordProducto!=null) {
				recordProducto.stream().forEach(p -> {
					ProdDto prodDto =  new ProdDto();
					prodDto.setId((p[0]).toString());
					prodDto.setNombre(p[1].toString());
					listProducto.add(prodDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
			exc.setDetail(procedureNameProducto + " : " + e.getMessage());
			exc.setConcreteException(e);
			throw exc;
		}

		return listProducto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GrupoMatrizDto> findAllGrupoMatriz(String codigoSubTipo,String codigoProducto) throws PcbsException {
		String procedureNameGrupoMatriz = propertiesSql.getLISTAR_GRUPOS_MATRIZ();
		List<GrupoMatrizDto> listGrupoMatriz =  new ArrayList<>();
		List<Object[]> recordGrupoMatriz=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameGrupoMatriz);	
			storedProcedureQuery.registerStoredProcedureParameter("codigoSubTipo", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("codigoProducto", String.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("codigoSubTipo",codigoSubTipo);
			storedProcedureQuery.setParameter("codigoProducto",codigoProducto);
			
			storedProcedureQuery.execute();
			recordGrupoMatriz = storedProcedureQuery.getResultList();
		
			if(recordGrupoMatriz!=null) {
				recordGrupoMatriz.stream().forEach(p -> {
					GrupoMatrizDto grupoMatrizDto =  new GrupoMatrizDto();
					grupoMatrizDto.setId(Long.valueOf((p[0]).toString()));
					grupoMatrizDto.setNombre(p[1].toString());
					listGrupoMatriz.add(grupoMatrizDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
			exc.setDetail(procedureNameGrupoMatriz + " : " + e.getMessage());
			exc.setConcreteException(e);
			throw exc;
		}

		return listGrupoMatriz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GrupoDto> findAllGrupo() throws PcbsException {
		String procedureNameGrupo = propertiesSql.getLISTAR_GRUPOS();
		List<GrupoDto> listGrupo =  new ArrayList<>();
		List<Object[]> recordGrupo=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameGrupo);	      
			storedProcedureQuery.execute();
			recordGrupo = storedProcedureQuery.getResultList();
		
			if(recordGrupo!=null) {
				recordGrupo.stream().forEach(p -> {
					GrupoDto grupoDto =  new GrupoDto();
					grupoDto.setId(Long.valueOf((p[0]).toString()));
					grupoDto.setNombre(p[1].toString());
					listGrupo.add(grupoDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
			exc.setDetail(procedureNameGrupo + " : " + e.getMessage());
			exc.setConcreteException(e);
			throw exc;
		}

		return listGrupo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EquivalenciaSeguroDto> findAllEquivalenciaSeguro(Long idCompania, Long idNegocio,Long idRamo) throws PcbsException {
		String procedureNameEquivalenciaSeguro = propertiesSql.getLISTAR_EQUIVALENCIAS_SEGUROS();
		List<EquivalenciaSeguroDto> listEquivalenciaSeguro =  new ArrayList<>();
		List<Object[]> recordEquivalenciaSeguro=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameEquivalenciaSeguro);
			storedProcedureQuery.registerStoredProcedureParameter("idCompania", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("idNegocio", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("idRamo", Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("idCompania",idCompania );
			storedProcedureQuery.setParameter("idNegocio",idNegocio );
			storedProcedureQuery.setParameter("idRamo",idRamo );
			storedProcedureQuery.execute();
			recordEquivalenciaSeguro = storedProcedureQuery.getResultList();
		
			if(recordEquivalenciaSeguro!=null) {
				recordEquivalenciaSeguro.stream().forEach(p -> {
					EquivalenciaSeguroDto equivalenciaSeguroDto =  new EquivalenciaSeguroDto();
					equivalenciaSeguroDto.setId(Long.valueOf((p[0]).toString()));
					equivalenciaSeguroDto.setNombre(p[1].toString());
					listEquivalenciaSeguro.add(equivalenciaSeguroDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
			exc.setDetail(procedureNameEquivalenciaSeguro + " : " + e.getMessage());
			exc.setConcreteException(e);
			throw exc;
		}

		return listEquivalenciaSeguro;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GrupoMejorOfertaDto> findAllGrupoMejorOferta() throws PcbsException {
		String procedureNameGrupoMejorOferta = propertiesSql.getLISTAR_GRUPOS_MEJOR_OFERTA();
		List<GrupoMejorOfertaDto> listGrupoMejorOferta =  new ArrayList<>();
		List<Object[]> recordGrupoMejorOferta=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameGrupoMejorOferta);	      
			storedProcedureQuery.execute();
			recordGrupoMejorOferta = storedProcedureQuery.getResultList();
		
			if(recordGrupoMejorOferta!=null) {
				recordGrupoMejorOferta.stream().forEach(p -> {
					GrupoMejorOfertaDto grupoMejorOfertaDto =  new GrupoMejorOfertaDto();
					grupoMejorOfertaDto.setId(Long.valueOf((p[0]).toString()));
					grupoMejorOfertaDto.setNombre(p[1].toString());
					listGrupoMejorOferta.add(grupoMejorOfertaDto); 
				});
			}
		}
		catch(Exception e) {
			PcbsException exc = new PcbsException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
			exc.setDetail(procedureNameGrupoMejorOferta + " : " + e.getMessage());
			exc.setConcreteException(e);
			throw exc;
		}

		return listGrupoMejorOferta;
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> findNumRut(String numRut, String digito) throws PcbsException {
		String procedureBuscaRut = propertiesSql.getBUSCAR_RUT();

		List<String> listNombreRut =  new ArrayList<>();
		List<Object[]> recordNombreRut=null;

		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaRut);
			storedProcedureQuery.registerStoredProcedureParameter("rut", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("digito", String.class, ParameterMode.IN);

			storedProcedureQuery.setParameter("rut",Long.parseLong(numRut) );
			storedProcedureQuery.setParameter("digito",digito );
			storedProcedureQuery.execute();
			recordNombreRut = storedProcedureQuery.getResultList();


			if(recordNombreRut!=null) {
				recordNombreRut.stream().forEach(p -> {
					listNombreRut.add((p[3]).toString());
				});
			}


		} catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}
		return listNombreRut;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String findRutProductManager(String numRut) throws PcbsException {
		String procedureBuscaRutProductManager = propertiesSql.getBUSCAR_RUT_SIN_DIGITO_PRODUCT_MANAGER();

		String rut="";
		List<Object[]> record=null;

		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaRutProductManager);
			storedProcedureQuery.registerStoredProcedureParameter("rut", String.class, ParameterMode.IN);

			storedProcedureQuery.setParameter("rut",numRut);
			storedProcedureQuery.execute();
			record = storedProcedureQuery.getResultList();


			if(record!=null) {
				if(!record.isEmpty()) {
					rut= String.valueOf(record.get(0));
				}
			}


		} catch(Exception e) {
			PcbsException exc = new PcbsException(e);
			throw exc;
		}
		return rut;
	}

	

	






	
}
