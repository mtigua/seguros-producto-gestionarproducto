package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.entities.TipoTasa;
import seguros.producto.gestionarproducto.repositories.TipoTasaRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoTasaException;
import seguros.producto.gestionarproducto.servicesImpl.TipoTasaServiceImpl;

public class TipoTasaServiceImplTest {

	@InjectMocks
	TipoTasaServiceImpl tipoTasaService;
	
	private TipoTasaRepository tipoTasaRepository= Mockito.mock(TipoTasaRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoTasaRepository.findAll()).thenReturn(getLista());
		
		List<TipoTasaDto> listaTipoTasaDto= tipoTasaService.findAll();
		
		assertEquals(2, listaTipoTasaDto.size());
	}
	
	@Test(expected = TipoTasaException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoTasaRepository.findAll()).thenThrow(TipoTasaException.class);		
		 tipoTasaService.findAll();
	}
	
	
	private List<TipoTasa> getLista() {
		List<TipoTasa> lista= new ArrayList<>();
		
		TipoTasa TipoTasa1= createTipoTasa(1L,"%","");
		TipoTasa TipoTasa2= createTipoTasa(2L,"xM","");
		
		lista.add(TipoTasa1);
		lista.add(TipoTasa2);
		
		return lista;
		
	}
	
	private TipoTasa createTipoTasa(Long id,String nombre,String descripcion) {
		TipoTasa TipoTasa= new TipoTasa();
		TipoTasa.setId(id);
		TipoTasa.setDescripcion(descripcion);
		TipoTasa.setNombre(nombre);
		return TipoTasa;
	}

}
