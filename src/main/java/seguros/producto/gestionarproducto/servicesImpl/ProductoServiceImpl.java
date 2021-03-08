package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.repositories.ProductoRepository;
import seguros.producto.gestionarproducto.services.ProductoService;


@Service
public class ProductoServiceImpl implements ProductoService {
	

	
	@Autowired
	private ProductoRepository productoRepository;
	
	

	@Override
	public List<ProductoDto> findAll() throws ProductoException {
		List<ProductoDto> list=new ArrayList<>();
		
		try {
			list= productoRepository.findAll().stream().map(item ->{
				ProductoDto p= new ProductoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			ProductoException exc = new ProductoException();
			exc.setErrorMessage(e.getClass().toString() + " " + e.getMessage());	        	
		    exc.setDetail( e.getLocalizedMessage());
			exc.setConcreteException(e);
			throw e;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
