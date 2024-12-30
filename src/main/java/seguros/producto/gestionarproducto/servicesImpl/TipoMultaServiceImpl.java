package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.repositories.TipoMultaRepository;
import seguros.producto.gestionarproducto.services.TipoMultaService;


@Service
public class TipoMultaServiceImpl implements TipoMultaService {
	

	
	@Autowired
	private TipoMultaRepository tipoMultaRepository;
	
	

	@Transactional
	@Override
	public List<TipoMultaDto> findAll() throws TipoMultaException {
		List<TipoMultaDto> list=new ArrayList<>();
		
		try {
			list= tipoMultaRepository.findAll().stream().map(item ->{
				TipoMultaDto p= new TipoMultaDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoMultaException exc = new TipoMultaException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
