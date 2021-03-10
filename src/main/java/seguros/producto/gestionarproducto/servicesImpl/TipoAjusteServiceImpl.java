package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoAjusteDto;
import seguros.producto.gestionarproducto.repositories.TipoAjusteRepository;
import seguros.producto.gestionarproducto.services.TipoAjusteService;


@Service
public class TipoAjusteServiceImpl implements TipoAjusteService {
	

	
	@Autowired
	private TipoAjusteRepository TipoAjusteRepository;
	
	
	@Transactional
	@Override
	public List<TipoAjusteDto> findAll() throws TipoAjusteException {
		List<TipoAjusteDto> list=new ArrayList<>();
		
		try {
			list= TipoAjusteRepository.findAll().stream().map(item ->{
				TipoAjusteDto p= new TipoAjusteDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoAjusteException exc = new TipoAjusteException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
