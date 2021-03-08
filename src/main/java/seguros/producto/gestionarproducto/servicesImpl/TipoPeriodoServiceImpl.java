package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoPeriodoDto;
import seguros.producto.gestionarproducto.repositories.TipoPeriodoRepository;
import seguros.producto.gestionarproducto.services.TipoPeriodoService;


@Service
public class TipoPeriodoServiceImpl implements TipoPeriodoService {
	

	
	@Autowired
	private TipoPeriodoRepository TipoPeriodoRepository;
	
	

	@Override
	public List<TipoPeriodoDto> findAll() throws TipoPeriodoException {
		List<TipoPeriodoDto> list=new ArrayList<>();
		
		try {
			list= TipoPeriodoRepository.findAll().stream().map(item ->{
				TipoPeriodoDto p= new TipoPeriodoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoPeriodoException exc = new TipoPeriodoException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
