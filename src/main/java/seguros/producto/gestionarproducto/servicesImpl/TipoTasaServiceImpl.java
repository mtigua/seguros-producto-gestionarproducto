package seguros.producto.gestionarproducto.servicesImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.repositories.TipoTasaRepository;
import seguros.producto.gestionarproducto.services.TipoTasaService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TipoTasaServiceImpl implements TipoTasaService {
	

	
	@Autowired
	private TipoTasaRepository tipoTasaRepository;
	
	
	@Transactional
	@Override
	public List<TipoTasaDto> findAll() throws TipoTasaException {
		List<TipoTasaDto> list=new ArrayList<>();
		
		try {
			list= tipoTasaRepository.findAll().stream().map(item ->{
				TipoTasaDto p= new TipoTasaDto();
				BeanUtils.copyProperties(item, p);
				return p;
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoTasaException exc = new TipoTasaException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
