package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoDescuentoDto;
import seguros.producto.gestionarproducto.entities.TipoDescuento;
import seguros.producto.gestionarproducto.repositories.TipoDescuentoRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoDescuentoException;
import seguros.producto.gestionarproducto.servicesImpl.TipoDescuentoServiceImpl;

public class TipoDescuentoServiceImplTest {

	@InjectMocks
	TipoDescuentoServiceImpl tipoDescuentoService;
	
	private TipoDescuentoRepository tipoDescuentoRepository= Mockito.mock(TipoDescuentoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoDescuentoRepository.findAll()).thenReturn(getLista());
		
		List<TipoDescuentoDto> listaTipoDescuentoDto= tipoDescuentoService.findAll();
		
		assertEquals(2, listaTipoDescuentoDto.size());
	}
	
	@Test(expected = TipoDescuentoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoDescuentoRepository.findAll()).thenThrow(TipoDescuentoException.class);		
		 tipoDescuentoService.findAll();
	}
	
	
	private List<TipoDescuento> getLista() {
		List<TipoDescuento> lista= new ArrayList<>();
		
		TipoDescuento TipoDescuento1= createTipoDescuento(1L,"Proporcional","");
		TipoDescuento TipoDescuento2= createTipoDescuento(2L,"Sobre cobertura base","");
		
		lista.add(TipoDescuento1);
		lista.add(TipoDescuento2);
		
		return lista;
		
	}
	
	private TipoDescuento createTipoDescuento(Long id,String nombre,String descripcion) {
		TipoDescuento TipoDescuento= new TipoDescuento();
		TipoDescuento.setId(id);
		TipoDescuento.setDescripcion(descripcion);
		TipoDescuento.setNombre(nombre);
		return TipoDescuento;
	}

}
