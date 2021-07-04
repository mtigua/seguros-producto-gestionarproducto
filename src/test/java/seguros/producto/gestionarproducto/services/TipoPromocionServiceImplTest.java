package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoPromocionDto;
import seguros.producto.gestionarproducto.entities.TipoPromocion;
import seguros.producto.gestionarproducto.repositories.TipoPromocionRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoPromocionException;
import seguros.producto.gestionarproducto.servicesImpl.TipoPromocionServiceImpl;

public class TipoPromocionServiceImplTest {

	@InjectMocks
	TipoPromocionServiceImpl tipoPromocionService;
	
	private TipoPromocionRepository tipoPromocionRepository= Mockito.mock(TipoPromocionRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoPromocionRepository.findAll()).thenReturn(getLista());
		
		List<TipoPromocionDto> listaTipoPromocionDto= tipoPromocionService.findAll();
		
		assertEquals(3, listaTipoPromocionDto.size());
	}
	
	@Test(expected = TipoPromocionException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoPromocionRepository.findAll()).thenThrow(TipoPromocionException.class);		
		 tipoPromocionService.findAll();
	}
	
	
	private List<TipoPromocion> getLista() {
		List<TipoPromocion> lista= new ArrayList<>();
		
		TipoPromocion TipoPromocion1= createTipoPromocion(1L,"Gift-Card","");
		TipoPromocion TipoPromocion2= createTipoPromocion(2L,"Puntos Nectar","");
		TipoPromocion TipoPromocion3= createTipoPromocion(3L,"Producto","");
		
		lista.add(TipoPromocion1);
		lista.add(TipoPromocion2);
		lista.add(TipoPromocion3);
		
		return lista;
		
	}
	
	private TipoPromocion createTipoPromocion(Long id,String nombre,String descripcion) {
		TipoPromocion TipoPromocion= new TipoPromocion();
		TipoPromocion.setId(id);
		TipoPromocion.setDescripcion(descripcion);
		TipoPromocion.setNombre(nombre);
		return TipoPromocion;
	}


}
