package seguros.producto.gestionarproducto.repositories;

import java.util.List;

import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;


public interface PCBSRepositoryCustom {
	
	public List<MonedaDto> findAllMoneda() throws PcbsException;
	public List<CompaniaDto> findAllCompania() throws PcbsException;
	public List<NegocioDto> findAllNegocioByCompania(Long idCompania) throws PcbsException;
	public List<RamoDto> findAllRamoByCompaniaNegocio(Long idCompania,Long idNegocio) throws PcbsException;
	
	
}
