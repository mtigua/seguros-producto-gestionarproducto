package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoPromocionDto;
import seguros.producto.gestionarproducto.repositories.TipoPromocionRepository;
import seguros.producto.gestionarproducto.services.TipoPromocionService;


@Service
public class TipoPromocionServiceImpl implements TipoPromocionService {
	

	
	@Autowired
	private TipoPromocionRepository TipoPromocionRepository;
	
	

	@Override
	public List<TipoPromocionDto> findAll() throws TipoPromocionException {
		List<TipoPromocionDto> list=new ArrayList<>();
		
		try {
			list= TipoPromocionRepository.findAll().stream().map(item ->{
				TipoPromocionDto p= new TipoPromocionDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoPromocionException exc = new TipoPromocionException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
