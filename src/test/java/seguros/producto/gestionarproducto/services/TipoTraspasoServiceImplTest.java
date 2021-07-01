package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoTraspasoDto;
import seguros.producto.gestionarproducto.entities.TipoTraspaso;
import seguros.producto.gestionarproducto.repositories.TipoTraspasoRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoTraspasoException;
import seguros.producto.gestionarproducto.servicesImpl.TipoTraspasoServiceImpl;

public class TipoTraspasoServiceImplTest {

	@InjectMocks
	TipoTraspasoServiceImpl tipoTraspasoService;
	
	private TipoTraspasoRepository tipoTraspasoRepository= Mockito.mock(TipoTraspasoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoTraspasoRepository.findAll()).thenReturn(getLista());
		
		List<TipoTraspasoDto> listaTipoTraspasoDto= tipoTraspasoService.findAll();
		
		assertEquals(2, listaTipoTraspasoDto.size());
	}
	
	@Test(expected = TipoTraspasoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoTraspasoRepository.findAll()).thenThrow(TipoTraspasoException.class);		
		 tipoTraspasoService.findAll();
	}
	
	
	private List<TipoTraspaso> getLista() {
		List<TipoTraspaso> lista= new ArrayList<>();
		
		TipoTraspaso TipoTraspaso1= createTipoTraspaso(1L,"Poliza Colectiva","");
		TipoTraspaso TipoTraspaso2= createTipoTraspaso(2L,"Póliza Individual","");
		
		lista.add(TipoTraspaso1);
		lista.add(TipoTraspaso2);
		
		return lista;
		
	}
	
	private TipoTraspaso createTipoTraspaso(Long id,String nombre,String descripcion) {
		TipoTraspaso TipoTraspaso= new TipoTraspaso();
		TipoTraspaso.setId(id);
		TipoTraspaso.setDescripcion(descripcion);
		TipoTraspaso.setNombre(nombre);
		return TipoTraspaso;
	}

}
