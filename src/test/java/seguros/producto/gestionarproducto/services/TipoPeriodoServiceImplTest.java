package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoPeriodoDto;
import seguros.producto.gestionarproducto.entities.TipoPeriodo;
import seguros.producto.gestionarproducto.repositories.TipoPeriodoRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoPeriodoException;
import seguros.producto.gestionarproducto.servicesImpl.TipoPeriodoServiceImpl;

public class TipoPeriodoServiceImplTest {

	@InjectMocks
	TipoPeriodoServiceImpl tipoPeriodoService;
	
	private TipoPeriodoRepository tipoPeriodoRepository= Mockito.mock(TipoPeriodoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoPeriodoRepository.findAll()).thenReturn(getLista());
		
		List<TipoPeriodoDto> listaTipoPeriodoDto= tipoPeriodoService.findAll();
		
		assertEquals(6, listaTipoPeriodoDto.size());
	}
	
	@Test(expected = TipoPeriodoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoPeriodoRepository.findAll()).thenThrow(TipoPeriodoException.class);		
		 tipoPeriodoService.findAll();
	}
	
	
	private List<TipoPeriodo> getLista() {
		List<TipoPeriodo> lista= new ArrayList<>();
		
		TipoPeriodo TipoPeriodo1= createTipoPeriodo(1L,"Cuotas","");
		TipoPeriodo TipoPeriodo2= createTipoPeriodo(2L,"Anual","");
		TipoPeriodo TipoPeriodo3= createTipoPeriodo(3L,"Semestral","");
		TipoPeriodo TipoPeriodo4= createTipoPeriodo(4L,"Trimestral","");
		TipoPeriodo TipoPeriodo5= createTipoPeriodo(5L,"Bimensual","");
		TipoPeriodo TipoPeriodo6= createTipoPeriodo(6L,"Mensual","");
		
		lista.add(TipoPeriodo1);
		lista.add(TipoPeriodo2);
		lista.add(TipoPeriodo3);
		lista.add(TipoPeriodo4);
		lista.add(TipoPeriodo5);
		lista.add(TipoPeriodo6);
		
		return lista;
		
	}
	
	private TipoPeriodo createTipoPeriodo(Long id,String nombre,String descripcion) {
		TipoPeriodo TipoPeriodo= new TipoPeriodo();
		TipoPeriodo.setId(id);
		TipoPeriodo.setDescripcion(descripcion);
		TipoPeriodo.setNombre(nombre);
		return TipoPeriodo;
	}

}
