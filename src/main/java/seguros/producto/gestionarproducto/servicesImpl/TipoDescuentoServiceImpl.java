package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoDescuentoDto;
import seguros.producto.gestionarproducto.repositories.TipoDescuentoRepository;
import seguros.producto.gestionarproducto.services.TipoDescuentoService;


@Service
public class TipoDescuentoServiceImpl implements TipoDescuentoService {
	

	
	@Autowired
	private TipoDescuentoRepository TipoDescuentoRepository;
	
	
	@Transactional
	@Override
	public List<TipoDescuentoDto> findAll() throws TipoDescuentoException {
		List<TipoDescuentoDto> list=new ArrayList<>();
		
		try {
			list= TipoDescuentoRepository.findAll().stream().map(item ->{
				TipoDescuentoDto p= new TipoDescuentoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoDescuentoException exc = new TipoDescuentoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
