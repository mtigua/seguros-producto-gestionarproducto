package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.entities.TipoSeguro;
import seguros.producto.gestionarproducto.repositories.TipoSeguroRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoSeguroException;
import seguros.producto.gestionarproducto.servicesImpl.TipoSeguroServiceImpl;

public class TipoSeguroServiceImplTest {

	@InjectMocks
	TipoSeguroServiceImpl tipoSeguroService;
	
	private TipoSeguroRepository tipoSeguroRepository= Mockito.mock(TipoSeguroRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoSeguroRepository.findAll()).thenReturn(getLista());
		
		List<TipoSeguroDto> listaTipoSeguroDto= tipoSeguroService.findAll();
		
		assertEquals(2, listaTipoSeguroDto.size());
	}
	
	@Test(expected = TipoSeguroException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoSeguroRepository.findAll()).thenThrow(TipoSeguroException.class);		
		 tipoSeguroService.findAll();
	}
	
	
	private List<TipoSeguro> getLista() {
		List<TipoSeguro> lista= new ArrayList<>();
		
		TipoSeguro TipoSeguro1= createTipoSeguro(1L,"Financiero","");
		TipoSeguro TipoSeguro2= createTipoSeguro(2L,"Tradicional","");
		
		lista.add(TipoSeguro1);
		lista.add(TipoSeguro2);
		
		return lista;
		
	}
	
	private TipoSeguro createTipoSeguro(Long id,String nombre,String descripcion) {
		TipoSeguro TipoSeguro= new TipoSeguro();
		TipoSeguro.setId(id);
		TipoSeguro.setDescripcion(descripcion);
		TipoSeguro.setNombre(nombre);
		return TipoSeguro;
	}

}
