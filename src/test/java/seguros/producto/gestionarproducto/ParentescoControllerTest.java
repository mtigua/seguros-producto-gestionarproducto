package seguros.producto.gestionarproducto;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.controllers.ParentescoController;
import seguros.producto.gestionarproducto.dto.ParentescoDto;
import seguros.producto.gestionarproducto.services.ParentescoService;
import seguros.producto.gestionarproducto.servicesImpl.ParentescoException;

public class ParentescoControllerTest {



	
	@InjectMocks
	ParentescoController parentescoController;
	
	private ParentescoService parentescoServiceMock= Mockito.mock(ParentescoService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetParentesco() {	
		 List<ParentescoDto> lista= getListaParentescoDtoMock();		
		 Mockito.when(parentescoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<ParentescoDto>> response = parentescoController.getParentesco();
		 assertEquals(6, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = ParentescoException.class)
	public void testWhenErrorParentescoExceptionGetParentesco() {			
		
		ParentescoException parentescoException = new ParentescoException();
		parentescoException.setErrorMessage("Error en el sistema");
		parentescoException.setConcreteException(new ParentescoException());		 
		parentescoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_parentesco()).thenReturn("Error listando parentesco");
		 Mockito.when(parentescoServiceMock.findAll()).thenThrow(parentescoException);
		 parentescoController.getParentesco();	
		 
	}

	
	private List<ParentescoDto> getListaParentescoDtoMock() {
		List<ParentescoDto> lista= new ArrayList<>();
		
		ParentescoDto parentescoDto1= new ParentescoDto(1L,"Conyuge","");
		ParentescoDto parentescoDto2= new ParentescoDto(2L,"Hijo","");
		ParentescoDto parentescoDto3= new ParentescoDto(3L,"Padre","");
		ParentescoDto parentescoDto4= new ParentescoDto(4L,"Madre","");
		ParentescoDto parentescoDto5= new ParentescoDto(5L,"Otro","");
		ParentescoDto parentescoDto6= new ParentescoDto(6L,"El mismo","");
		
		lista.add(parentescoDto1);
		lista.add(parentescoDto2);
		lista.add(parentescoDto3);
		lista.add(parentescoDto4);
		lista.add(parentescoDto5);
		lista.add(parentescoDto6);
		
		return lista;
		
	}


}
