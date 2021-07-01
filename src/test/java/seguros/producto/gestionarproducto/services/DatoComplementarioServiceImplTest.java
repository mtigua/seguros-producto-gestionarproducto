package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.DatoComplementarioDto;
import seguros.producto.gestionarproducto.entities.DatoComplementario;
import seguros.producto.gestionarproducto.repositories.DatoComplementarioRepository;
import seguros.producto.gestionarproducto.servicesImpl.DatoComplementarioException;
import seguros.producto.gestionarproducto.servicesImpl.DatoComplementarioServiceImpl;

public class DatoComplementarioServiceImplTest {


	@InjectMocks
	DatoComplementarioServiceImpl datoComplementarioService;
	
	private DatoComplementarioRepository datoComplementarioRepository= Mockito.mock(DatoComplementarioRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(datoComplementarioRepository.findAll()).thenReturn(getLista());
		
		List<DatoComplementarioDto> listaDatoComplementarioDto= datoComplementarioService.findAll();
		
		assertEquals(2, listaDatoComplementarioDto.size());
	}
	
	@Test(expected = DatoComplementarioException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(datoComplementarioRepository.findAll()).thenThrow(DatoComplementarioException.class);		
		 datoComplementarioService.findAll();
	}
	
	
	private List<DatoComplementario> getLista() {
		List<DatoComplementario> lista= new ArrayList<>();
		
		DatoComplementario DatoComplementario1= createDatoComplementario(1L,"Coberturas","");
		DatoComplementario DatoComplementario2= createDatoComplementario(2L,"Criterio","");
		
		lista.add(DatoComplementario1);
		lista.add(DatoComplementario2);
		
		return lista;
		
	}
	
	private DatoComplementario createDatoComplementario(Long id,String nombre,String descripcion) {
		DatoComplementario DatoComplementario= new DatoComplementario();
		DatoComplementario.setId(id);
		DatoComplementario.setDescripcion(descripcion);
		DatoComplementario.setNombre(nombre);
		return DatoComplementario;
	}


}
