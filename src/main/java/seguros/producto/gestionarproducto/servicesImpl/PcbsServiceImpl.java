package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.repositories.PCBSRepositoryCustom;
import seguros.producto.gestionarproducto.services.PcbsService;


@Service
public class PcbsServiceImpl implements PcbsService {
	

	
	@Autowired
	private PCBSRepositoryCustom pcbsRepositoryCustom;	
	

	@Override
	@Transactional
	public List<MonedaDto> findAllMonedas() throws PcbsException {
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
	public List<CompaniaDto> findAllCompanias() throws PcbsException {
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
	public List<NegocioDto> findAllNegociosByCompania(Long idCompania) throws PcbsException {
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
	public List<RamoDto> findAllRamosByCompaniaNegocio(Long idCompania, Long idNegocio) throws PcbsException {
		List<RamoDto> list=new ArrayList<>();
		
		try {
			list= pcbsRepositoryCustom.findAllRamoByCompaniaNegocio(idCompania, idNegocio);
		}
		catch(PcbsException e) {
			throw e;
		}
		return list;
	}

	@Transactional
	@Override
	public Integer findNumPoliza(String numPoliza) throws PcbsException {
        Integer existe = null;
		
		try {
			existe= pcbsRepositoryCustom.findNumPoliza(numPoliza);
		}
		catch(PcbsException e) {
			throw e;
		}
		return existe;
	}

	

	

	
	
	


	
}
