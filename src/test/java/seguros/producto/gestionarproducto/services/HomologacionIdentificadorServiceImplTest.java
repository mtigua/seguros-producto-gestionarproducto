package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.HomologacionIdentificadorDto;
import seguros.producto.gestionarproducto.entities.HomologacionIdentificador;
import seguros.producto.gestionarproducto.repositories.HomologacionIdentificadorRepository;
import seguros.producto.gestionarproducto.servicesImpl.HomologacionIdentificadorException;
import seguros.producto.gestionarproducto.servicesImpl.HomologacionIdentificadorServiceImpl;

public class HomologacionIdentificadorServiceImplTest {

	@InjectMocks
	HomologacionIdentificadorServiceImpl homologacionIdentificadorService;
	
	private HomologacionIdentificadorRepository homologacionIdentificadorRepository= Mockito.mock(HomologacionIdentificadorRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(homologacionIdentificadorRepository.findAll()).thenReturn(getLista());
		
		List<HomologacionIdentificadorDto> listaHomologacionIdentificadorDto= homologacionIdentificadorService.findAll();
		
		assertEquals(1, listaHomologacionIdentificadorDto.size());
	}
	
	@Test(expected = HomologacionIdentificadorException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(homologacionIdentificadorRepository.findAll()).thenThrow(HomologacionIdentificadorException.class);		
		 homologacionIdentificadorService.findAll();
	}
	
	
	private List<HomologacionIdentificador> getLista() {
		List<HomologacionIdentificador> lista= new ArrayList<>();
		
		HomologacionIdentificador HomologacionIdentificador1= createHomologacionIdentificador(1L,"Compania","");
		
		lista.add(HomologacionIdentificador1);
		
		return lista;
		
	}
	
	private HomologacionIdentificador createHomologacionIdentificador(Long id,String terminoDestino,String terminoOrigen) {
		HomologacionIdentificador HomologacionIdentificador= new HomologacionIdentificador();
		HomologacionIdentificador.setId(id);
		HomologacionIdentificador.setTerminoDestino(terminoDestino);
		HomologacionIdentificador.setTerminoOrigen(terminoOrigen);
		return HomologacionIdentificador;
	}

}
