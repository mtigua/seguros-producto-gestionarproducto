package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.MonedaDto;
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
	
	

	

	
	
	


	
}
