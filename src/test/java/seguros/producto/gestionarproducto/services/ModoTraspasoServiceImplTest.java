package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.ModoTraspasoDto;
import seguros.producto.gestionarproducto.entities.ModoTraspaso;
import seguros.producto.gestionarproducto.repositories.ModoTraspasoRepository;
import seguros.producto.gestionarproducto.servicesImpl.ModoTraspasoException;
import seguros.producto.gestionarproducto.servicesImpl.ModoTraspasoServiceImpl;

public class ModoTraspasoServiceImplTest {

	@InjectMocks
	ModoTraspasoServiceImpl modoTraspasoService;
	
	private ModoTraspasoRepository modoTraspasoRepository= Mockito.mock(ModoTraspasoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(modoTraspasoRepository.findAll()).thenReturn(getLista());
		
		List<ModoTraspasoDto> listaModoTraspasoDto= modoTraspasoService.findAll();
		
		assertEquals(2, listaModoTraspasoDto.size());
	}
	
	@Test(expected = ModoTraspasoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(modoTraspasoRepository.findAll()).thenThrow(ModoTraspasoException.class);		
		 modoTraspasoService.findAll();
	}
	
	
	private List<ModoTraspaso> getLista() {
		List<ModoTraspaso> lista= new ArrayList<>();
		
		ModoTraspaso ModoTraspaso1= createModoTraspaso(1L,"Identificación fijo","");
		ModoTraspaso ModoTraspaso2= createModoTraspaso(2L,"Identificación libre","");
		
		lista.add(ModoTraspaso1);
		lista.add(ModoTraspaso2);
		
		return lista;
		
	}
	
	private ModoTraspaso createModoTraspaso(Long id,String nombre,String descripcion) {
		ModoTraspaso ModoTraspaso= new ModoTraspaso();
		ModoTraspaso.setId(id);
		ModoTraspaso.setDescripcion(descripcion);
		ModoTraspaso.setNombre(nombre);
		return ModoTraspaso;
	}

}
