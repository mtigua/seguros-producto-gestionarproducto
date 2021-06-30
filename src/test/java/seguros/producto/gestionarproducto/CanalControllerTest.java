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
import seguros.producto.gestionarproducto.services.CanalService;
import seguros.producto.gestionarproducto.servicesImpl.CanalException;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.controllers.CanalController;
import seguros.producto.gestionarproducto.dto.CanalDto;


public class CanalControllerTest {

	@InjectMocks
	CanalController canalController;
	
	private CanalService canalServiceMock= Mockito.mock(CanalService.class);
	

	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetCanal() {	
		 List<CanalDto> lista= getListaCanalesDtoMock();		
		 Mockito.when(canalServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<CanalDto>> response = canalController.getCanal();
		 assertEquals(5, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = CanalException.class)
	public void testWhenErrorCanalExceptionGetCanal() {			
		
		 CanalException canalException = new CanalException();
		 canalException.setErrorMessage("Error en el sistema");
		 canalException.setConcreteException(new CanalException());		 
		 canalException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_canal()).thenReturn("Error listando canales");
		 Mockito.when(canalServiceMock.findAll()).thenThrow(canalException);
		 canalController.getCanal();	 
		 
	}
		
	private List<CanalDto> getListaCanalesDtoMock() {
		List<CanalDto> lista= new ArrayList<>();
		
		CanalDto canalDto1= new CanalDto(1L,"AS400","",null);
		CanalDto canalDto2= new CanalDto(2L,"WEB","",null);
		CanalDto canalDto3= new CanalDto(3L,"TREBOL","",null);
		CanalDto canalDto4= new CanalDto(4L,"PCBS","",null);
		CanalDto canalDto5= new CanalDto(5L,"APOLO","",null);
		
		lista.add(canalDto1);
		lista.add(canalDto2);
		lista.add(canalDto3);
		lista.add(canalDto4);
		lista.add(canalDto5);
		
		return lista;
		
	}


	

}
