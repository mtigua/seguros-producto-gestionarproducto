package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import seguros.producto.gestionarproducto.dto.TarifaPorDto;
import seguros.producto.gestionarproducto.entities.TarifaPor;
import seguros.producto.gestionarproducto.repositories.TarifaPorRepository;
import seguros.producto.gestionarproducto.servicesImpl.TarifaPorException;
import seguros.producto.gestionarproducto.servicesImpl.TarifaPorServiceImpl;


public class TarifaPorServiceImplTest {

	@InjectMocks
	TarifaPorServiceImpl tarifaPorService;
	
	private TarifaPorRepository tarifaPorRepository= Mockito.mock(TarifaPorRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tarifaPorRepository.findAll()).thenReturn(getLista());
		
		List<TarifaPorDto> listaTarifaPorDto= tarifaPorService.findAll();
		
		assertEquals(2, listaTarifaPorDto.size());
	}
	
	@Test(expected = TarifaPorException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tarifaPorRepository.findAll()).thenThrow(TarifaPorException.class);		
		 tarifaPorService.findAll();
	}
	
	
	private List<TarifaPor> getLista() {
		List<TarifaPor> lista= new ArrayList<>();
		
		TarifaPor TarifaPor1= createTarifaPor(1L,"Por Plan","");
		TarifaPor TarifaPor2= createTarifaPor(2L,"Por Coberturas","");
		
		lista.add(TarifaPor1);
		lista.add(TarifaPor2);
		
		return lista;
		
	}
	
	private TarifaPor createTarifaPor(Long id,String nombre,String descripcion) {
		TarifaPor TarifaPor= new TarifaPor();
		TarifaPor.setId(id);
		TarifaPor.setDescripcion(descripcion);
		TarifaPor.setNombre(nombre);
		return TarifaPor;
	}

}
