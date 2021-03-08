package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoTarifaDto;
import seguros.producto.gestionarproducto.repositories.TipoTarifaRepository;
import seguros.producto.gestionarproducto.services.TipoTarifaService;


@Service
public class TipoTarifaServiceImpl implements TipoTarifaService {
	

	
	@Autowired
	private TipoTarifaRepository TipoTarifaRepository;
	
	

	@Override
	public List<TipoTarifaDto> findAll() throws TipoTarifaException {
		List<TipoTarifaDto> list=new ArrayList<>();
		
		try {
			list= TipoTarifaRepository.findAll().stream().map(item ->{
				TipoTarifaDto p= new TipoTarifaDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoTarifaException exc = new TipoTarifaException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
