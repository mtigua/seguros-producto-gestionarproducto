package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.HomologacionIdentificadorDto;
import seguros.producto.gestionarproducto.repositories.HomologacionIdentificadorRepository;
import seguros.producto.gestionarproducto.services.HomologacionIdentificadorService;


@Service
public class HomologacionIdentificadorServiceImpl implements HomologacionIdentificadorService {
	

	
	@Autowired
	private HomologacionIdentificadorRepository HomologacionIdentificadorRepository;
	
	
	@Transactional
	@Override
	public List<HomologacionIdentificadorDto> findAll() throws HomologacionIdentificadorException {
		List<HomologacionIdentificadorDto> list=new ArrayList<>();
		
		try {
			list= HomologacionIdentificadorRepository.findAll().stream().map(item ->{
				HomologacionIdentificadorDto p= new HomologacionIdentificadorDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			HomologacionIdentificadorException exc = new HomologacionIdentificadorException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
