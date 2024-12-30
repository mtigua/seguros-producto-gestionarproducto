package seguros.producto.gestionarproducto.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.DestinoVentaDto;
import seguros.producto.gestionarproducto.repositories.DestinoVentaRepository;
import seguros.producto.gestionarproducto.services.DestinoVentaService;


@Service
public class DestinoVentaServiceImpl implements DestinoVentaService {
	

	
	@Autowired
	private DestinoVentaRepository destinoVentaRepository;
	
	

	@Transactional
	@Override
	public List<DestinoVentaDto> findAll() throws DestinoVentaException {
		List<DestinoVentaDto> list=new ArrayList<>();
		
		try {
			list= destinoVentaRepository.findAll().stream().map(item ->{
				DestinoVentaDto p= new DestinoVentaDto();
				BeanUtils.copyProperties(item, p);
				return p;
				 
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			DestinoVentaException exc = new DestinoVentaException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
