package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.repositories.TipoSeguroRepository;
import seguros.producto.gestionarproducto.services.TipoSeguroService;


@Service
public class TipoSeguroServiceImpl implements TipoSeguroService {
	

	
	@Autowired
	private TipoSeguroRepository TipoSeguroRepository;
	
	
	@Transactional
	@Override
	public List<TipoSeguroDto> findAll() throws TipoSeguroException {
		List<TipoSeguroDto> list=new ArrayList<>();
		
		try {
			list= TipoSeguroRepository.findAll().stream().map(item ->{
				TipoSeguroDto p= new TipoSeguroDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoSeguroException exc = new TipoSeguroException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
