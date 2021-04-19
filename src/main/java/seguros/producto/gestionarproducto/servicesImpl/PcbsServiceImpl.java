package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.repositories.PCBSRepositoryCustom;
import seguros.producto.gestionarproducto.services.PcbsService;


@Service
public class PcbsServiceImpl implements PcbsService {



	@Autowired
	private PCBSRepositoryCustom pcbsRepositoryCustom;	
	

	@Override
	@Transactional
	public List<MonedaDto> findAllMoneda() throws PcbsException {
		List<MonedaDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllMoneda();
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
		
	}


	@Override
	@Transactional
	public List<CompaniaDto> findAllCompania() throws PcbsException {
		List<CompaniaDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllCompania();
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<NegocioDto> findAllNegocioByCompania(Long idCompania) throws PcbsException {
		List<NegocioDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllNegocioByCompania(idCompania);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<RamoDto> findAllRamoByCompaniaNegocio(Long idCompania, Long idNegocio) throws PcbsException {
		List<RamoDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllRamoByCompaniaNegocio(idCompania, idNegocio);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<SubtipoDto> findAllSubtipoByCompaniaRamo(Long idCompania,Long idRamo) throws PcbsException {
		List<SubtipoDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllSubtipoByCompaniaRamo(idCompania,idRamo);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<ProdDto> findAllProductoBySubtipo(String codigoSubTipo) throws PcbsException {
		List<ProdDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllProductoBySubtipo(codigoSubTipo);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<GrupoMatrizDto> findAllGrupoMatriz(String codigoSubTipo,String codigoProducto) throws PcbsException {
		List<GrupoMatrizDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllGrupoMatriz(codigoSubTipo,codigoProducto);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<GrupoDto> findAllGrupo() throws PcbsException {
		List<GrupoDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllGrupo();
		}	
		catch(PcbsException e) {
				throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<EquivalenciaSeguroDto> findAllEquivalenciaSeguro(Long idCompania, Long idNegocio, Long idRamo)
			throws PcbsException {
		List<EquivalenciaSeguroDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllEquivalenciaSeguro(idCompania, idNegocio, idRamo);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Override
	@Transactional
	public List<GrupoMejorOfertaDto> findAllGrupoMejorOferta() throws PcbsException {
		List<GrupoMejorOfertaDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllGrupoMejorOferta();
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}

	@Transactional
	@Override
	public Integer findNumPoliza(String numPoliza, String digito, Long idCompania, Long idNegocio, Long idRamo) throws PcbsException {
        Integer existe = null;
		
		try {
			existe= pcbsRepositoryCustom.findNumPoliza(numPoliza, digito, idCompania, idNegocio, idRamo);
		}
		catch(PcbsException e) {
			throw e;
		}
		return existe;
	}

	@Transactional
	@Override
	public List<RutDto> findNumRut(String numRut, String digito) throws PcbsException {
		List<RutDto> nombres;

		try {
			nombres= pcbsRepositoryCustom.findNumRut(numRut, digito);
		}
		catch(PcbsException e) {
			throw e;
		}
		return nombres;
	}

	@Transactional
	@Override
	public List<PlanCoberturaDto> listPlan(Long numRamo) throws PcbsException {
		List<PlanCoberturaDto> plans;

		try {
			plans= pcbsRepositoryCustom.listPlan(numRamo);
		} catch(PcbsException e) {
			throw e;
		}
		return plans;
	}

	@Transactional
	@Override
	public List<AsociadoDto> getAsociados() throws PcbsException {
		List<AsociadoDto> asociados;

		try {
			asociados = pcbsRepositoryCustom.getAsociado();
		} catch(PcbsException e) {
			throw e;
		}
		return asociados;
	}

	@Transactional
	@Override
	public List<AsociadoDto> getAsociadosEmision() throws PcbsException {
		List<AsociadoDto> asociados;

		try {
			asociados = pcbsRepositoryCustom.getAsociadoEmision();
		} catch(PcbsException e) {
			throw e;
		}
		return asociados;
	}

	@Transactional
	@Override
	public List<CatalogoCantidadCuotasDto> getCatalogoCantidadCuotas() throws PcbsException {
		List<CatalogoCantidadCuotasDto> list = new ArrayList<>();
		try {
			list = pcbsRepositoryCustom.getCatalogoCuotas();
		} catch(PcbsException e) {
			throw e;
		}
		return list;
	}


	@Transactional
	@Override
	public List<CatalogoCantidadCuotasDto> getCatalogoCantidadCuotasWebPay() throws PcbsException {
		List<CatalogoCantidadCuotasDto> list;
		try {
			list = pcbsRepositoryCustom.getCatalogoCuotasPayWeb();
		} catch(PcbsException e) {
			throw e;
		}
		return list;
	}

	@Transactional
	@Override
	public String findRutProductManager(String numRut) throws PcbsException {
		 String rut = "";
			
			try {
				rut= pcbsRepositoryCustom.findRutProductManager(numRut);
			}
			catch(PcbsException e) {
				throw e;
			}
			return rut;
	}

	
	@Transactional
	@Override
	public Integer decryptPasswordProductManager(String rut, String password) {
		 Integer valid = 0;
			
			try {
				valid= pcbsRepositoryCustom.decryptPasswordProductManager(rut,password);
			}
			catch(PcbsException e) {
				throw e;
			}
			return valid;
	}
	

	
}
