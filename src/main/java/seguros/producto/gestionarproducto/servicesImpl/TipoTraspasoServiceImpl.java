package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoTraspasoDto;
import seguros.producto.gestionarproducto.repositories.TipoTraspasoRepository;
import seguros.producto.gestionarproducto.services.TipoTraspasoService;


@Service
public class TipoTraspasoServiceImpl implements TipoTraspasoService {
	

	
	@Autowired
	private TipoTraspasoRepository tipoTraspasoRepository;
	
	
	@Transactional
	@Override
	public List<TipoTraspasoDto> findAll() throws TipoTraspasoException {
		List<TipoTraspasoDto> list=new ArrayList<>();
		
		try {
			list= tipoTraspasoRepository.findAll().stream().map(item ->{
				TipoTraspasoDto p= new TipoTraspasoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoTraspasoException exc = new TipoTraspasoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
