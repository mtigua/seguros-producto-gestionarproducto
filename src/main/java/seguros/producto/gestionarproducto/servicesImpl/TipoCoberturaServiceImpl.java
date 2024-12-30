package seguros.producto.gestionarproducto.servicesImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seguros.producto.gestionarproducto.dto.TipoCoberturaDto;
import seguros.producto.gestionarproducto.repositories.TipoCoberturaRepository;
import seguros.producto.gestionarproducto.services.TipoCoberturaService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TipoCoberturaServiceImpl implements TipoCoberturaService {
	

	
	@Autowired
	private TipoCoberturaRepository tipoSeguroRepository;
	
	
	@Transactional
	@Override
	public List<TipoCoberturaDto> findAll() throws TipoCoberturaException {
		List<TipoCoberturaDto> list=new ArrayList<>();
		
		try {
			list= tipoSeguroRepository.findAll().stream().map(item ->{
				TipoCoberturaDto p= new TipoCoberturaDto();
				BeanUtils.copyProperties(item, p);
				return p;
			}).collect(Collectors.toList());;
		}
		catch(Exception e) {
			TipoCoberturaException exc = new TipoCoberturaException(e);
			throw exc;
		}
		return list;
		
	}
	
	

	

	
	
	


	
}
