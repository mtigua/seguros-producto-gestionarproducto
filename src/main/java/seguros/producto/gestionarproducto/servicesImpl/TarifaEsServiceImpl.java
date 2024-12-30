package seguros.producto.gestionarproducto.servicesImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.services.TarifaEsService;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seguros.producto.gestionarproducto.repositories.TarifaEsRepository;


@Service
public class TarifaEsServiceImpl implements TarifaEsService {
	

	
	@Autowired
	private TarifaEsRepository tarifaEsRepository;
	
	
	@Transactional
	@Override
	public List<TarifaEsDto> findAll() throws TarifaEsException {
		List<TarifaEsDto> list=new ArrayList<>();
		
		try {
			list= tarifaEsRepository.findAll().stream().map(item ->{
				TarifaEsDto p= new TarifaEsDto();
				BeanUtils.copyProperties(item, p);
				return p;
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TarifaEsException exc = new TarifaEsException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
