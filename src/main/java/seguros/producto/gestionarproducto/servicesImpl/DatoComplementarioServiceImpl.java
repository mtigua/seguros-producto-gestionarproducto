package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.DatoComplementarioDto;
import seguros.producto.gestionarproducto.repositories.DatoComplementarioRepository;
import seguros.producto.gestionarproducto.services.DatoComplementarioService;


@Service
public class DatoComplementarioServiceImpl implements DatoComplementarioService {
	

	
	@Autowired
	private DatoComplementarioRepository datoComplementarioRepository;
	
	

	@Transactional
	@Override
	public List<DatoComplementarioDto> findAll() throws DatoComplementarioException {
		List<DatoComplementarioDto> list=new ArrayList<>();
		
		try {
			list= datoComplementarioRepository.findAll().stream().map(item ->{
				DatoComplementarioDto p= new DatoComplementarioDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			DatoComplementarioException exc = new DatoComplementarioException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
