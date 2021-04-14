package seguros.producto.gestionarproducto.services;

import java.util.List;

import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

public interface PcbsService {
	
	public List<MonedaDto> findAllMoneda() throws PcbsException;
	public List<CompaniaDto> findAllCompania() throws PcbsException;
	public List<NegocioDto> findAllNegocioByCompania(Long idCompania) throws PcbsException;
	public List<RamoDto> findAllRamoByCompaniaNegocio(Long idCompania,Long idNegocio) throws PcbsException;
	public List<SubtipoDto> findAllSubtipoByCompaniaRamo(Long idCompania,Long idRamo) throws PcbsException;
	public List<ProdDto> findAllProductoBySubtipo(String codigoSubTipo ) throws PcbsException;
	public List<GrupoMatrizDto> findAllGrupoMatriz(String codigoSubTipo,String codigoProducto) throws PcbsException;
	public List<GrupoDto> findAllGrupo() throws PcbsException;
	public List<EquivalenciaSeguroDto> findAllEquivalenciaSeguro(Long idCompania, Long idNegocio,Long idRamo) throws PcbsException;
	public List<GrupoMejorOfertaDto> findAllGrupoMejorOferta() throws PcbsException;
	public Integer findNumPoliza(String numPoliza, String digito, Long idCompania, Long idNegocio, Long idRamo) throws PcbsException;
	public List<String> findNumRut(String numRut, String digito) throws PcbsException;
	public List<AsociadoDto> getAsociados() throws PcbsException;
	public List<AsociadoDto> getAsociadosEmision() throws PcbsException;
	public List<CatalogoCantidadCuotasDto> getCatalogoCantidadCuotas() throws PcbsException;
	public List<CatalogoCantidadCuotasDto> getCatalogoCantidadCuotasWebPay() throws PcbsException;
	public String findRutProductManager(String numRut) throws PcbsException;
	public Integer decryptPasswordProductManager(String rut,String password);
}
