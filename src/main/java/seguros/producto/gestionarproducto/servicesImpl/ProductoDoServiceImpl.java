package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.FormDataDescripcionOperativaDto;
import seguros.producto.gestionarproducto.repositories.ProductoDoRepository;
import seguros.producto.gestionarproducto.services.ProductoDoService;


@Service
public class ProductoDoServiceImpl implements ProductoDoService {
	

	
	@Autowired
	private ProductoDoRepository productoDoRepository;
	
	
	@Transactional
	@Override
	public List<FormDataDescripcionOperativaDto> findAll() throws ProductoDoException {
		List<FormDataDescripcionOperativaDto> list=new ArrayList<>();
		
		try {
			list= productoDoRepository.findAll().stream().map(item ->{
				FormDataDescripcionOperativaDto p= new FormDataDescripcionOperativaDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			ProductoDoException exc = new ProductoDoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
