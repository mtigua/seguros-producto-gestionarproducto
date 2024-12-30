package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import seguros.producto.gestionarproducto.dto.ParentescoDto;
import seguros.producto.gestionarproducto.entities.Parentesco;
import seguros.producto.gestionarproducto.repositories.ParentescoRepository;
import seguros.producto.gestionarproducto.servicesImpl.ParentescoException;
import seguros.producto.gestionarproducto.servicesImpl.ParentescoServiceImpl;


public class ParentescoServiceImplTest {

	@InjectMocks
	ParentescoServiceImpl parentescoService;
	
	private ParentescoRepository parentescoRepository= Mockito.mock(ParentescoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(parentescoRepository.findAll()).thenReturn(getLista());
		
		List<ParentescoDto> listaParentescoDto= parentescoService.findAll();
		
		assertEquals(6, listaParentescoDto.size());
	}
	
	@Test(expected = ParentescoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(parentescoRepository.findAll()).thenThrow(ParentescoException.class);		
		 parentescoService.findAll();
	}
	
	
	private List<Parentesco> getLista() {
		List<Parentesco> lista= new ArrayList<>();
		
		Parentesco Parentesco1= createParentesco(1L,"Conyuge","");
		Parentesco Parentesco2= createParentesco(2L,"Hijo","");
		Parentesco Parentesco3= createParentesco(3L,"Padre","");
		Parentesco Parentesco4= createParentesco(4L,"Madre","");
		Parentesco Parentesco5= createParentesco(5L,"Otro","");
		Parentesco Parentesco6= createParentesco(6L,"El mismo","");
		
		lista.add(Parentesco1);
		lista.add(Parentesco2);
		lista.add(Parentesco3);
		lista.add(Parentesco4);
		lista.add(Parentesco5);
		lista.add(Parentesco6);
		
		return lista;
		
	}
	
	private Parentesco createParentesco(Long id,String nombre,String descripcion) {
		Parentesco Parentesco= new Parentesco();
		Parentesco.setId(id);
		Parentesco.setDescripcion(descripcion);
		Parentesco.setNombre(nombre);
		return Parentesco;
	}

}
