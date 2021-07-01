package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.entities.TarifaEs;
import seguros.producto.gestionarproducto.repositories.TarifaEsRepository;
import seguros.producto.gestionarproducto.servicesImpl.TarifaEsException;
import seguros.producto.gestionarproducto.servicesImpl.TarifaEsServiceImpl;

public class TarifaEsServiceImplTest {

	@InjectMocks
	TarifaEsServiceImpl tarifaEsService;
	
	private TarifaEsRepository tarifaEsRepository= Mockito.mock(TarifaEsRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tarifaEsRepository.findAll()).thenReturn(getLista());
		
		List<TarifaEsDto> listaTarifaEsDto= tarifaEsService.findAll();
		
		assertEquals(2, listaTarifaEsDto.size());
	}
	
	@Test(expected = TarifaEsException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tarifaEsRepository.findAll()).thenThrow(TarifaEsException.class);		
		 tarifaEsService.findAll();
	}
	
	
	private List<TarifaEs> getLista() {
		List<TarifaEs> lista= new ArrayList<>();
		
		TarifaEs TarifaEs1= createTarifaEs(1L,"Valor","");
		TarifaEs TarifaEs2= createTarifaEs(2L,"Tasa","");
		
		lista.add(TarifaEs1);
		lista.add(TarifaEs2);
		
		return lista;
		
	}
	
	private TarifaEs createTarifaEs(Long id,String nombre,String descripcion) {
		TarifaEs TarifaEs= new TarifaEs();
		TarifaEs.setId(id);
		TarifaEs.setDescripcion(descripcion);
		TarifaEs.setNombre(nombre);
		return TarifaEs;
	}

}
