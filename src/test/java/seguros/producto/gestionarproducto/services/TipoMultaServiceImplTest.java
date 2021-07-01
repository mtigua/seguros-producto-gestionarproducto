package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.entities.TipoMulta;
import seguros.producto.gestionarproducto.repositories.TipoMultaRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoMultaException;
import seguros.producto.gestionarproducto.servicesImpl.TipoMultaServiceImpl;

public class TipoMultaServiceImplTest {

	@InjectMocks
	TipoMultaServiceImpl tipoMultaService;
	
	private TipoMultaRepository tipoMultaRepository= Mockito.mock(TipoMultaRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoMultaRepository.findAll()).thenReturn(getLista());
		
		List<TipoMultaDto> listaTipoMultaDto= tipoMultaService.findAll();
		
		assertEquals(3, listaTipoMultaDto.size());
	}
	
	@Test(expected = TipoMultaException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoMultaRepository.findAll()).thenThrow(TipoMultaException.class);		
		 tipoMultaService.findAll();
	}
	
	
	private List<TipoMulta> getLista() {
		List<TipoMulta> lista= new ArrayList<>();
		
		TipoMulta TipoMulta1= createTipoMulta(1L,"Monto","");
		TipoMulta TipoMulta2= createTipoMulta(2L,"Tabla","");
		TipoMulta TipoMulta3= createTipoMulta(3L,"Cuotas","");
		
		lista.add(TipoMulta1);
		lista.add(TipoMulta2);
		lista.add(TipoMulta3);
		
		return lista;
		
	}
	
	private TipoMulta createTipoMulta(Long id,String nombre,String descripcion) {
		TipoMulta TipoMulta= new TipoMulta();
		TipoMulta.setId(id);
		TipoMulta.setDescripcion(descripcion);
		TipoMulta.setNombre(nombre);
		return TipoMulta;
	}

}
