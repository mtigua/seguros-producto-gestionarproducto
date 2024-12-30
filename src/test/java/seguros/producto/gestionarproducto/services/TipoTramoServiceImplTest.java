package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.entities.TipoTramo;
import seguros.producto.gestionarproducto.repositories.TipoTramoRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoTramoException;
import seguros.producto.gestionarproducto.servicesImpl.TipoTramoServiceImpl;

public class TipoTramoServiceImplTest {

	@InjectMocks
	TipoTramoServiceImpl tipoTramoService;
	
	private TipoTramoRepository tipoTramoRepository= Mockito.mock(TipoTramoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoTramoRepository.findAll()).thenReturn(getLista());
		
		List<TipoTramoDto> listaTipoTramoDto= tipoTramoService.findAll(3L);
		
		assertEquals(2, listaTipoTramoDto.size());
	}
	
	@Test(expected = TipoTramoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoTramoRepository.findAll()).thenThrow(TipoTramoException.class);		
		 tipoTramoService.findAll(3L);
	}
	
	
	private List<TipoTramo> getLista() {
		List<TipoTramo> lista= new ArrayList<>();
		
		TipoTramo TipoTramo1= createTipoTramo(1L,"Edad","");
		TipoTramo TipoTramo2= createTipoTramo(2L,"Monto","");
		
		lista.add(TipoTramo1);
		lista.add(TipoTramo2);
		
		return lista;
		
	}
	
	private TipoTramo createTipoTramo(Long id,String nombre,String descripcion) {
		TipoTramo TipoTramo= new TipoTramo();
		TipoTramo.setId(id);
		TipoTramo.setDescripcion(descripcion);
		TipoTramo.setNombre(nombre);
		return TipoTramo;
	}

}
