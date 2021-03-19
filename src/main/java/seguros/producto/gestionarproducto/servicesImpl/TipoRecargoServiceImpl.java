package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoRecargoDto;
import seguros.producto.gestionarproducto.repositories.TipoRecargoRepository;
import seguros.producto.gestionarproducto.services.TipoRecargoService;


@Service
public class TipoRecargoServiceImpl implements TipoRecargoService {
	

	
	@Autowired
	private TipoRecargoRepository TipoRecargoRepository;
	
	
	@Transactional
	@Override
	public List<TipoRecargoDto> findAll() throws TipoRecargoException {
		List<TipoRecargoDto> list=new ArrayList<>();
		
		try {
			list= TipoRecargoRepository.findAll().stream().map(item ->{
				TipoRecargoDto p= new TipoRecargoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoRecargoException exc = new TipoRecargoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
