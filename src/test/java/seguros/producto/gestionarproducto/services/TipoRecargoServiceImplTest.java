package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoRecargoDto;
import seguros.producto.gestionarproducto.entities.TipoRecargo;
import seguros.producto.gestionarproducto.repositories.TipoRecargoRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoRecargoException;
import seguros.producto.gestionarproducto.servicesImpl.TipoRecargoServiceImpl;

public class TipoRecargoServiceImplTest {

	@InjectMocks
	TipoRecargoServiceImpl tipoRecargoService;
	
	private TipoRecargoRepository tipoRecargoRepository= Mockito.mock(TipoRecargoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoRecargoRepository.findAll()).thenReturn(getLista());
		
		List<TipoRecargoDto> listaTipoRecargoDto= tipoRecargoService.findAll();
		
		assertEquals(2, listaTipoRecargoDto.size());
	}
	
	@Test(expected = TipoRecargoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoRecargoRepository.findAll()).thenThrow(TipoRecargoException.class);		
		 tipoRecargoService.findAll();
	}
	
	
	private List<TipoRecargo> getLista() {
		List<TipoRecargo> lista= new ArrayList<>();
		
		TipoRecargo TipoRecargo1= createTipoRecargo(1L,"Proporcional","");
		TipoRecargo TipoRecargo2= createTipoRecargo(2L,"Sobre cobertura base","");
		
		lista.add(TipoRecargo1);
		lista.add(TipoRecargo2);
		
		return lista;
		
	}
	
	private TipoRecargo createTipoRecargo(Long id,String nombre,String descripcion) {
		TipoRecargo TipoRecargo= new TipoRecargo();
		TipoRecargo.setId(id);
		TipoRecargo.setDescripcion(descripcion);
		TipoRecargo.setNombre(nombre);
		return TipoRecargo;
	}

}
