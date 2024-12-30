package seguros.producto.gestionarproducto.servicesImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.ParentescoDto;
import seguros.producto.gestionarproducto.repositories.ParentescoRepository;
import seguros.producto.gestionarproducto.services.ParentescoService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParentescoServiceImpl implements ParentescoService {
	
	@Autowired
	private ParentescoRepository parentescoRepository;

	@Transactional
	@Override
	public List<ParentescoDto> findAll() throws ParentescoException {
		List<ParentescoDto> list=new ArrayList<>();
		
		try {
			list= parentescoRepository.findAll().stream().map(item ->{
				ParentescoDto p= new ParentescoDto();
				BeanUtils.copyProperties(item, p);
				return p;
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			ParentescoException exc = new ParentescoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
