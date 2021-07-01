package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoAjusteDto;
import seguros.producto.gestionarproducto.entities.TipoAjuste;
import seguros.producto.gestionarproducto.repositories.TipoAjusteRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoAjusteException;
import seguros.producto.gestionarproducto.servicesImpl.TipoAjusteServiceImpl;

public class TipoAjusteServiceImplTest {

	@InjectMocks
	TipoAjusteServiceImpl tipoAjusteService;
	
	private TipoAjusteRepository tipoAjusteRepository= Mockito.mock(TipoAjusteRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoAjusteRepository.findAll()).thenReturn(getLista());
		
		List<TipoAjusteDto> listaTipoAjusteDto= tipoAjusteService.findAll();
		
		assertEquals(2, listaTipoAjusteDto.size());
	}
	
	@Test(expected = TipoAjusteException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoAjusteRepository.findAll()).thenThrow(TipoAjusteException.class);		
		 tipoAjusteService.findAll();
	}
	
	
	private List<TipoAjuste> getLista() {
		List<TipoAjuste> lista= new ArrayList<>();
		
		TipoAjuste TipoAjuste1= createTipoAjuste(1L,"Afecto","");
		TipoAjuste TipoAjuste2= createTipoAjuste(2L,"Exento","");
		
		lista.add(TipoAjuste1);
		lista.add(TipoAjuste2);
		
		return lista;
		
	}
	
	private TipoAjuste createTipoAjuste(Long id,String nombre,String descripcion) {
		TipoAjuste TipoAjuste= new TipoAjuste();
		TipoAjuste.setId(id);
		TipoAjuste.setDescripcion(descripcion);
		TipoAjuste.setNombre(nombre);
		return TipoAjuste;
	}

}
