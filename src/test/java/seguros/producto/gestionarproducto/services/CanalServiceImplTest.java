package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import seguros.producto.gestionarproducto.dto.CanalDto;
import seguros.producto.gestionarproducto.entities.Canal;
import seguros.producto.gestionarproducto.entities.DatoComplementario;
import seguros.producto.gestionarproducto.repositories.CanalRepository;
import seguros.producto.gestionarproducto.servicesImpl.CanalException;
import seguros.producto.gestionarproducto.servicesImpl.CanalServiceImpl;

public class CanalServiceImplTest {

	@InjectMocks
	CanalServiceImpl canalService;
	
	private CanalRepository canalRepository= Mockito.mock(CanalRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(canalRepository.findAll()).thenReturn(getLista());
		
		List<CanalDto> listaCanalDto= canalService.findAll();
		
		assertEquals(5, listaCanalDto.size());
	}
	
	@Test(expected = CanalException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(canalRepository.findAll()).thenThrow(CanalException.class);		
		 canalService.findAll();
	}
	
	
	private List<Canal> getLista() {
		List<Canal> lista= new ArrayList<>();
		
		Canal canal1= createCanal(1L,"AS400","",new HashSet<DatoComplementario>());
		Canal canal2= createCanal(2L,"WEB","",new HashSet<DatoComplementario>());
		Canal canal3= createCanal(3L,"TREBOL","",new HashSet<DatoComplementario>());
		Canal canal4= createCanal(4L,"PCBS","",new HashSet<DatoComplementario>());
		Canal canal5= createCanal(5L,"APOLO","",new HashSet<DatoComplementario>());
		
		lista.add(canal1);
		lista.add(canal2);
		lista.add(canal3);
		lista.add(canal4);
		lista.add(canal5);	
		
		return lista;
		
	}
	
	private Canal createCanal(Long id,String nombre,String descripcion,Set<DatoComplementario> datosComplementarios) {
		Canal canal= new Canal();
		canal.setId(id);
		canal.setDescripcion(descripcion);
		canal.setNombre(nombre);
		canal.setDatosComplementarios(datosComplementarios);
		return canal;
	}

}
