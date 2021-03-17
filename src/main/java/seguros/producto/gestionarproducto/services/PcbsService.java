package seguros.producto.gestionarproducto.services;

import java.util.List;

import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

public interface PcbsService {
	
	public List<MonedaDto> findAllMonedas() throws PcbsException;
	public List<CompaniaDto> findAllCompanias() throws PcbsException;
	public List<NegocioDto> findAllNegociosByCompania(Long idCompania) throws PcbsException;
	public List<RamoDto> findAllRamosByCompaniaNegocio(Long idCompania,Long idNegocio) throws PcbsException;
	public Integer findNumPoliza(String numPoliza) throws PcbsException;
}
