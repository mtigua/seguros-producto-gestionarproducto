package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.entities.PrimaSobreQue;
import seguros.producto.gestionarproducto.repositories.PrimaSobreQueRepository;
import seguros.producto.gestionarproducto.servicesImpl.PrimaSobreQueException;
import seguros.producto.gestionarproducto.servicesImpl.PrimaSobreQueServiceImpl;

public class PrimaSobreQueServiceImplTest {

	@InjectMocks
	PrimaSobreQueServiceImpl primaSobreQueService;
	
	private PrimaSobreQueRepository primaSobreQueRepository= Mockito.mock(PrimaSobreQueRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(primaSobreQueRepository.findAll()).thenReturn(getLista());
		
		List<PrimaSobreQueDto> listaPrimaSobreQueDto= primaSobreQueService.findAll();
		
		assertEquals(4, listaPrimaSobreQueDto.size());
	}
	
	@Test(expected = PrimaSobreQueException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(primaSobreQueRepository.findAll()).thenThrow(PrimaSobreQueException.class);		
		 primaSobreQueService.findAll();
	}
	
	
	private List<PrimaSobreQue> getLista() {
		List<PrimaSobreQue> lista= new ArrayList<>();
		
		PrimaSobreQue PrimaSobreQue1= createPrimaSobreQue(1L,"Edificio","");
		PrimaSobreQue PrimaSobreQue2= createPrimaSobreQue(2L,"Contenido","");
		PrimaSobreQue PrimaSobreQue3= createPrimaSobreQue(3L,"Edificio+Contenido","");
		PrimaSobreQue PrimaSobreQue4= createPrimaSobreQue(4L,"Robo","");
		
		lista.add(PrimaSobreQue1);
		lista.add(PrimaSobreQue2);
		lista.add(PrimaSobreQue3);
		lista.add(PrimaSobreQue4);
		
		return lista;
		
	}
	
	private PrimaSobreQue createPrimaSobreQue(Long id,String nombre,String descripcion) {
		PrimaSobreQue PrimaSobreQue= new PrimaSobreQue();
		PrimaSobreQue.setId(id);
		PrimaSobreQue.setDescripcion(descripcion);
		PrimaSobreQue.setNombre(nombre);
		return PrimaSobreQue;
	}

}
