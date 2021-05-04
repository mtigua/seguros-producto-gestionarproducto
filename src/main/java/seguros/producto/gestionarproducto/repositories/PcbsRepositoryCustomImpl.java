package seguros.producto.gestionarproducto.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.configuration.PropertiesSql;
import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

@Repository
public class PcbsRepositoryCustomImpl implements PCBSRepositoryCustom{
	
	private static final String VALUE_UNDEFINED="No se ha podido indentificar valor para el par\u00E1metro de salida: ";
	static final String COMPANY = "idCompania";
	static final String BUSINESS = "idNegocio";
	static final String RAMO = "idRamo";
	static final String CODIGOSUBTIPO = "codigoSubTipo";
	static final String CODIGOPRODUCTO = "codigoProducto";
	static final String EXISTE = "existe";
	static final String VALID = "valid";
	static final String RESULT = "result";

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
				throw new PcbsException(e);
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
					companiaDto.setTipo(p[2].toString());
					listCompania.add(companiaDto); 
				});
			}
		}
		catch(Exception e) {
			throw new PcbsException(e);
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
			storedProcedureQuery.registerStoredProcedureParameter(COMPANY, Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter(COMPANY,idCompania );
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
			throw new PcbsException(e);
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
			recordRamo = getStoreProcedureCompanyBusiness(idCompania, idNegocio, procedureNameRamo, BUSINESS);

			if(recordRamo!=null) {
				recordRamo.stream().forEach(p -> {
					RamoDto ramoDto =  new RamoDto();
					ramoDto.setId(Long.valueOf((p[0]).toString()));
					ramoDto.setNombre(p[1].toString());
					ramoDto.setTipo(p[2].toString());
					listRamo.add(ramoDto); 
				});
			}
		}
		catch(Exception e) {
			throw new PcbsException(e);
		}

		return listRamo;
	}

	private List<Object[]> getStoreProcedureCompanyBusiness(Long idCompania, Long idNegocioOrRamo, String procedureNameRamo, String businessOrRamo) {
		List<Object[]> recordRamo;
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureNameRamo);
		storedProcedureQuery.registerStoredProcedureParameter(COMPANY, Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(businessOrRamo, Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(COMPANY,idCompania );
		storedProcedureQuery.setParameter(businessOrRamo,idNegocioOrRamo );
		storedProcedureQuery.execute();
		recordRamo = storedProcedureQuery.getResultList();
		return recordRamo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubtipoDto> findAllSubtipoByCompaniaRamo(Long idCompania,Long idRamo) throws PcbsException {
		String procedureNameSubtipo = propertiesSql.getLISTAR_SUBTIPOS_POR_COMPANIA_RAMO();
		List<SubtipoDto> listSubtipo =  new ArrayList<>();
		List<Object[]> recordSubtipo=null;
		 
		try {
			recordSubtipo = getStoreProcedureCompanyBusiness(idCompania, idRamo, procedureNameSubtipo, RAMO);

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
			storedProcedureQuery.registerStoredProcedureParameter(CODIGOSUBTIPO, String.class, ParameterMode.IN);
			storedProcedureQuery.setParameter(CODIGOSUBTIPO,codigoSubTipo);
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
			storedProcedureQuery.registerStoredProcedureParameter(CODIGOSUBTIPO, String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter(CODIGOPRODUCTO, String.class, ParameterMode.IN);
			storedProcedureQuery.setParameter(CODIGOSUBTIPO,codigoSubTipo);
			storedProcedureQuery.setParameter(CODIGOPRODUCTO,codigoProducto);
			
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
			storedProcedureQuery.registerStoredProcedureParameter(COMPANY, Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter(BUSINESS, Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter(RAMO, Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter(COMPANY,idCompania );
			storedProcedureQuery.setParameter(BUSINESS,idNegocio );
			storedProcedureQuery.setParameter(RAMO,idRamo );
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
	public Integer findNumPoliza(String numPoliza, String digito, Long idCompania, Long idNegocio, Long idRamo) throws PcbsException {
		String procedureBuscaPoliza = propertiesSql.getBUSCAR_POLIZA();
		Integer existe=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaPoliza);
			storedProcedureQuery.registerStoredProcedureParameter("numPoliza", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("numDigito", String.class, ParameterMode.IN);

			storedProcedureQuery.registerStoredProcedureParameter("compania", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("ramo", Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("negocio", Long.class, ParameterMode.IN);

			storedProcedureQuery.registerStoredProcedureParameter(EXISTE, Integer.class, ParameterMode.OUT);
			
			storedProcedureQuery.setParameter("numPoliza",numPoliza );
			storedProcedureQuery.setParameter("numDigito", digito.equals("null") ? "" : digito);

			storedProcedureQuery.setParameter("compania", idCompania );
			storedProcedureQuery.setParameter("ramo", idRamo );
			storedProcedureQuery.setParameter("negocio", idNegocio );
			storedProcedureQuery.execute();
		
			Object object= storedProcedureQuery.getOutputParameterValue(EXISTE);

			  if(object!=null) {
				  existe= (int) storedProcedureQuery.getOutputParameterValue(EXISTE);
			  } else {
				    PcbsException exc = new PcbsException();
					exc.setErrorMessage(VALUE_UNDEFINED + EXISTE);
					exc.setDetail(VALUE_UNDEFINED + EXISTE);
					exc.setConcreteException(exc);
					throw exc;
			  }		
		
		}
		catch(PcbsException e){
			throw e;
		}
		catch(Exception e) {
			throw new PcbsException(e);
		}

		return existe;
	}

	@Override
	@Transactional
	public Integer findCodigoPos(String numCodigoPos ) throws PcbsException {
		String procedureBuscaPoliza = propertiesSql.getVALIDAR_BUSCAR_CODIGOPOS();
		Integer existe=null;

		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaPoliza);
			storedProcedureQuery.registerStoredProcedureParameter("codigoPos", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter(EXISTE, Integer.class, ParameterMode.OUT);

			storedProcedureQuery.setParameter("codigoPos",numCodigoPos );
			storedProcedureQuery.execute();

			Object object= storedProcedureQuery.getOutputParameterValue(EXISTE);

			if(object!=null) {
				existe= (int) storedProcedureQuery.getOutputParameterValue(EXISTE);
			} else {
				PcbsException exc = new PcbsException();
				exc.setErrorMessage(VALUE_UNDEFINED + EXISTE);
				exc.setDetail(VALUE_UNDEFINED + EXISTE);
				exc.setConcreteException(exc);
				throw exc;
			}

		} catch(PcbsException e) {
			throw e;
		} catch(Exception e) {
			throw new PcbsException(e);
		}

		return existe;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RutDto> findNumRut(String numRut, String digito) throws PcbsException {
		String procedureBuscaRut = propertiesSql.getBUSCAR_RUT();

		List<RutDto> listNombreRut =  new ArrayList<>();
		List<Object[]> recordNombreRut=null;

		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaRut);
			storedProcedureQuery.registerStoredProcedureParameter("rut", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("digito", String.class, ParameterMode.IN);

			storedProcedureQuery.setParameter("rut", numRut );
			storedProcedureQuery.setParameter("digito",digito );
			storedProcedureQuery.execute();
			recordNombreRut = storedProcedureQuery.getResultList();

			if(recordNombreRut!=null) {
				recordNombreRut.stream().forEach(p -> {
					RutDto rut = new RutDto();
					rut.setNombre(p[3].toString());
					rut.setDigito(p[2].toString());
					listNombreRut.add(rut);
				});
			}


		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return listNombreRut;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PlanCoberturaDto> listPlan(Long numRamo) throws PcbsException {
		String procedurePlan = propertiesSql.getLISTAR_PLAN_COBERTURA();

		List<PlanCoberturaDto> listPlan =  new ArrayList<>();
		List<Object[]> recordPlans=null;

		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedurePlan);
			storedProcedureQuery.registerStoredProcedureParameter("ramo", Long.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("ramo", numRamo );
			storedProcedureQuery.execute();
			recordPlans = storedProcedureQuery.getResultList();

			if(recordPlans!=null) {
				recordPlans.stream().forEach(p -> {
					PlanCoberturaDto plan = new PlanCoberturaDto();
					plan.setMnemonic((p[0]).toString());
					String stringToConvert = String.valueOf(p[1]);
					plan.setCorrelative(Long.parseLong(stringToConvert));
					plan.setDescription((p[2]).toString());
					listPlan.add(plan);
				});
			}
		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return listPlan;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AsociadoDto> getAsociado() throws PcbsException {
		String listarAsociado = propertiesSql.getLISTAR_ASOCIADO();

		List<AsociadoDto> asociados =  new ArrayList<>();
		List<Object[]> record = null;
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(listarAsociado);
			storedProcedureQuery.execute();
			record = storedProcedureQuery.getResultList();

			if(record!=null) {
				record.stream().forEach(p -> {
					AsociadoDto asociadoDto = new AsociadoDto();
					asociadoDto.setCode(p[0].toString());
					asociadoDto.setDescription(p[1].toString());
					asociados.add(asociadoDto);
				});
			}
		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return asociados;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AsociadoDto> getAsociadoEmision() throws PcbsException {
		String listarAsociado = propertiesSql.getLISTAR_ASOCIADO_EMISION();

		List<AsociadoDto> asociados =  new ArrayList<>();
		List<Object[]> record = null;
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(listarAsociado);
			storedProcedureQuery.execute();
			record = storedProcedureQuery.getResultList();

			if(record!=null) {
				record.stream().forEach(p -> {
					AsociadoDto asociadoDto = new AsociadoDto();
					asociadoDto.setCode(p[0].toString());
					asociadoDto.setDescription(p[1].toString());
					asociados.add(asociadoDto);
				});
			}
		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return asociados;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CatalogoCantidadCuotasDto> getCatalogoCuotas() throws PcbsException {
		List<CatalogoCantidadCuotasDto> catalogoCantidadCuotasDtos = new ArrayList<>();
		try {
			String sqlQuery = "SELECT new seguros.producto.gestionarproducto.dto.CatalogoCantidadCuotasDto(c.id, c.name) FROM catalogo_cantidad_cuotas c";
			TypedQuery<CatalogoCantidadCuotasDto> typedQuery = entityManager.createQuery(
					sqlQuery, CatalogoCantidadCuotasDto.class);
			catalogoCantidadCuotasDtos = typedQuery.getResultList();
		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return catalogoCantidadCuotasDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CatalogoCantidadCuotasDto> getCatalogoCuotasPayWeb() throws PcbsException {
		List<CatalogoCantidadCuotasDto> catalogoCantidadCuotasDtos = new ArrayList<>();
		try {
			String sqlQuery = "SELECT new seguros.producto.gestionarproducto.dto.CatalogoCantidadCuotasDto(c.id, c.name) FROM catalogo_cantidad_cuotas_webpay c";
			TypedQuery<CatalogoCantidadCuotasDto> typedQuery = entityManager.createQuery(
					sqlQuery, CatalogoCantidadCuotasDto.class);
			catalogoCantidadCuotasDtos = typedQuery.getResultList();
		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return catalogoCantidadCuotasDtos;
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


			if (record != null && !record.isEmpty()) {
				rut = String.valueOf(record.get(0));
			}


		} catch(Exception e) {
			throw new PcbsException(e);
		}
		return rut;
	}

	
	@Override
	@Transactional
	public Integer decryptPasswordProductManager(String rut, String password) throws PcbsException {
		String procedureBuscaPoliza = propertiesSql.getDESENCRIPTAR();
		Integer existe=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureBuscaPoliza);
			storedProcedureQuery.registerStoredProcedureParameter("password", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("rut", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter(VALID, Integer.class, ParameterMode.OUT);
			
			storedProcedureQuery.setParameter("rut",rut );
			storedProcedureQuery.setParameter("password",password );
			storedProcedureQuery.execute();
		
			Object result= storedProcedureQuery.getOutputParameterValue(VALID);
			  if(result!=null) {
				  existe= (int) storedProcedureQuery.getOutputParameterValue(VALID);
			  }
			  else {
				    PcbsException exc = new PcbsException();
					exc.setErrorMessage(VALUE_UNDEFINED + EXISTE);
					exc.setDetail(VALUE_UNDEFINED + EXISTE);
					exc.setConcreteException(exc);
					throw exc;
			  }		
		
		}
		catch(PcbsException e){
			throw e;
		}
		catch(Exception e) {
			throw new PcbsException(e);
		}

		return existe;
	}

	@Override
	public String generateNemotecnico() throws PcbsException {
		String generateNemotecnico = propertiesSql.getGENERATE_NEMOTECNICO();
		String newNemotecnico=null;
		 
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(generateNemotecnico);
			storedProcedureQuery.registerStoredProcedureParameter(RESULT, String.class, ParameterMode.OUT);
			
			storedProcedureQuery.execute();
		
			Object result= storedProcedureQuery.getOutputParameterValue(RESULT);
			  if(result!=null) {
				  newNemotecnico=  String.valueOf(storedProcedureQuery.getOutputParameterValue(RESULT));
			  }
			  else {
				    PcbsException exc = new PcbsException();
					exc.setErrorMessage(VALUE_UNDEFINED + EXISTE);
					exc.setDetail(VALUE_UNDEFINED + EXISTE);
					exc.setConcreteException(exc);
					throw exc;
			  }		
		
		}
		catch(PcbsException e){
			throw e;
		}
		catch(Exception e) {
			throw new PcbsException(e);
		}

		return newNemotecnico;
	}

	

	






	
}
