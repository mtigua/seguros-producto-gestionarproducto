package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoTarifaDto;
import seguros.producto.gestionarproducto.entities.TipoTarifa;
import seguros.producto.gestionarproducto.repositories.TipoTarifaRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoTarifaException;
import seguros.producto.gestionarproducto.servicesImpl.TipoTarifaServiceImpl;

public class TipoTarifaServiceImplTest {

	@InjectMocks
	TipoTarifaServiceImpl tipoTarifaService;
	
	private TipoTarifaRepository tipoTarifaRepository= Mockito.mock(TipoTarifaRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoTarifaRepository.findAll()).thenReturn(getLista());
		
		List<TipoTarifaDto> listaTipoTarifaDto= tipoTarifaService.findAll();
		
		assertEquals(4, listaTipoTarifaDto.size());
	}
	
	@Test(expected = TipoTarifaException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoTarifaRepository.findAll()).thenThrow(TipoTarifaException.class);		
		 tipoTarifaService.findAll();
	}
	
	
	private List<TipoTarifa> getLista() {
		List<TipoTarifa> lista= new ArrayList<>();
		
		TipoTarifa TipoTarifa1= createTipoTarifa(1L,"Por Tramo","");
		TipoTarifa TipoTarifa2= createTipoTarifa(2L,"Fija","");
		TipoTarifa TipoTarifa3= createTipoTarifa(3L,"Por Marca - Modelo - Año","");
		TipoTarifa TipoTarifa4= createTipoTarifa(4L,"Por Tabla Detalle","");
		
		lista.add(TipoTarifa1);
		lista.add(TipoTarifa2);
		lista.add(TipoTarifa3);
		lista.add(TipoTarifa4);
		
		return lista;
		
	}
	
	private TipoTarifa createTipoTarifa(Long id,String nombre,String descripcion) {
		TipoTarifa TipoTarifa= new TipoTarifa();
		TipoTarifa.setId(id);
		TipoTarifa.setDescripcion(descripcion);
		TipoTarifa.setNombre(nombre);
		return TipoTarifa;
	}

}
