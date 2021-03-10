package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.ConceptoDto;
import seguros.producto.gestionarproducto.repositories.ConceptoRepository;
import seguros.producto.gestionarproducto.services.ConceptoService;


@Service
public class ConceptoServiceImpl implements ConceptoService {
	

	
	@Autowired
	private ConceptoRepository conceptoRepository;
	
	
	@Transactional
	@Override
	public List<ConceptoDto> findAll() throws ConceptoException {
		List<ConceptoDto> list=new ArrayList<>();
		
		try {
			list= conceptoRepository.findAll().stream().map(item ->{
				ConceptoDto p= new ConceptoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			ConceptoException exc = new ConceptoException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
