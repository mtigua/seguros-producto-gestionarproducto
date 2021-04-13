package seguros.producto.gestionarproducto.repositories;

import java.util.List;

import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;


public interface PCBSRepositoryCustom {
	
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
	List<String> findNumRut(String numRut, String digito) throws PcbsException;
	List<AsociadoDto> getAsociado() throws PcbsException;
	List<AsociadoDto> getAsociadoEmision() throws PcbsException;
	List<CatalogoCantidadCuotasDto> getCatalogoCuotas() throws PcbsException;
	List<CatalogoCantidadCuotasDto> getCatalogoCuotasPayWeb() throws PcbsException;
	public String findRutProductManager(String numRut) throws PcbsException;
	public Integer decryptPasswordProductManager(String rut, String password) throws PcbsException;
	public String generateNemotecnico() throws PcbsException;
	
}
