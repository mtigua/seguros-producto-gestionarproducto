package seguros.producto.gestionarproducto.servicesImpl;


import java.util.Arrays;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.services.TipoTramoService;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seguros.producto.gestionarproducto.repositories.TipoTramoRepository;


@Service
public class TipoTramoServiceImpl implements TipoTramoService {
	

	
	@Autowired
	private TipoTramoRepository tipoTramoRepository;
	
	
	@Transactional
	@Override
	public List<TipoTramoDto> findAll(Long tipoRamo) throws TipoTramoException {
		List<TipoTramoDto> list=new ArrayList<>();
		
		Long[] ramosNoEdad= {1L,2L};
		
		try {
			list= tipoTramoRepository.findAll().stream().map(item ->{
				TipoTramoDto p= new TipoTramoDto();
				BeanUtils.copyProperties(item, p);
				return p;
			}).collect(Collectors.toList());
			
			if(tipoRamo!=null && Arrays.asList(ramosNoEdad).contains(tipoRamo) ) {
				list = list.stream().filter(item-> !item.getId().equals(2L) ).collect(Collectors.toList());
			}
		}
		catch(Exception e) {
			TipoTramoException exc = new TipoTramoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
