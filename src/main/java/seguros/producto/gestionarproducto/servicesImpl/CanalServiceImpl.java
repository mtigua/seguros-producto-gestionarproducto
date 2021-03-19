package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.CanalDto;
import seguros.producto.gestionarproducto.repositories.CanalRepository;
import seguros.producto.gestionarproducto.services.CanalService;


@Service
public class CanalServiceImpl implements CanalService {
	

	
	@Autowired
	private CanalRepository canalRepository;
	
	

	@Transactional
	@Override
	public List<CanalDto> findAll() throws CanalException {
		List<CanalDto> list=new ArrayList<>();
		
		try {
			list= canalRepository.findAll().stream().map(item ->{
				CanalDto p= new CanalDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			CanalException exc = new CanalException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
