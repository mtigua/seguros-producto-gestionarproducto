package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.ModoTraspasoDto;
import seguros.producto.gestionarproducto.repositories.ModoTraspasoRepository;
import seguros.producto.gestionarproducto.services.ModoTraspasoService;


@Service
public class ModoTraspasoServiceImpl implements ModoTraspasoService {
	

	
	@Autowired
	private ModoTraspasoRepository modoTraspasoRepository;
	
	

	@Transactional
	@Override
	public List<ModoTraspasoDto> findAll() throws ModoTraspasoException {
		List<ModoTraspasoDto> list=new ArrayList<>();
		
		try {
			list= modoTraspasoRepository.findAll().stream().map(item ->{
				ModoTraspasoDto p= new ModoTraspasoDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			ModoTraspasoException exc = new ModoTraspasoException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
