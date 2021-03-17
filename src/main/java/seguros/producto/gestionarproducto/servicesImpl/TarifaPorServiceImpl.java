package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TarifaPorDto;
import seguros.producto.gestionarproducto.repositories.TarifaPorRepository;
import seguros.producto.gestionarproducto.services.TarifaPorService;


@Service
public class TarifaPorServiceImpl implements TarifaPorService {
	

	
	@Autowired
	private TarifaPorRepository TarifaPorRepository;
	
	
	@Transactional
	@Override
	public List<TarifaPorDto> findAll() throws TarifaPorException {
		List<TarifaPorDto> list=new ArrayList<>();
		
		try {
			list= TarifaPorRepository.findAll().stream().map(item ->{
				TarifaPorDto p= new TarifaPorDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TarifaPorException exc = new TarifaPorException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
