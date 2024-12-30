package seguros.producto.gestionarproducto.servicesImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.repositories.PrimaSobreQueRepository;
import seguros.producto.gestionarproducto.services.PrimaSobreQueService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PrimaSobreQueServiceImpl implements PrimaSobreQueService {

	@Autowired
	private PrimaSobreQueRepository primaSobreQueRepository;

	@Transactional
	@Override
	public List<PrimaSobreQueDto> findAll() throws PrimaSobreQueException {
		List<PrimaSobreQueDto> list=new ArrayList<>();
		
		try {
			list= primaSobreQueRepository.findAll().stream().map(item ->{
				PrimaSobreQueDto p= new PrimaSobreQueDto();
				BeanUtils.copyProperties(item, p);
				return p;
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			PrimaSobreQueException exc = new PrimaSobreQueException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
