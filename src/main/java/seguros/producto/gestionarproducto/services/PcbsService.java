package seguros.producto.gestionarproducto.services;

import java.util.List;

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
}
