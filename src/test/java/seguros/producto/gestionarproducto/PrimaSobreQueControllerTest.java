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
import seguros.producto.gestionarproducto.controllers.PrimaSobreQueController;
import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.services.PrimaSobreQueService;
import seguros.producto.gestionarproducto.servicesImpl.PrimaSobreQueException;

public class PrimaSobreQueControllerTest {



	
	@InjectMocks
	PrimaSobreQueController primaSobreQueController;
	
	private PrimaSobreQueService primaSobreQueServiceMock= Mockito.mock(PrimaSobreQueService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetPrimaSobreQue() {	
		 List<PrimaSobreQueDto> lista= getListaPrimaSobreQueDtoMock();		
		 Mockito.when(primaSobreQueServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<PrimaSobreQueDto>> response = primaSobreQueController.getPrimaSobreQue();
		 assertEquals(4, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = PrimaSobreQueException.class)
	public void testWhenErrorPrimaSobreQueExceptionGetPrimaSobreQue() {			
		
		PrimaSobreQueException primaSobreQueException = new PrimaSobreQueException();
		primaSobreQueException.setErrorMessage("Error en el sistema");
		primaSobreQueException.setConcreteException(new PrimaSobreQueException());		 
		primaSobreQueException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_prima_sobre_que()).thenReturn("Error listando prima sobre que");
		 Mockito.when(primaSobreQueServiceMock.findAll()).thenThrow(primaSobreQueException);
		 primaSobreQueController.getPrimaSobreQue();	
		 
	}

	
	private List<PrimaSobreQueDto> getListaPrimaSobreQueDtoMock() {
		List<PrimaSobreQueDto> lista= new ArrayList<>();
		
		PrimaSobreQueDto primaSobreQueDto1= new PrimaSobreQueDto(1L,"Edificio","");
		PrimaSobreQueDto primaSobreQueDto2= new PrimaSobreQueDto(2L,"Contenido","");
		PrimaSobreQueDto primaSobreQueDto3= new PrimaSobreQueDto(3L,"Edificio+Contenido","");
		PrimaSobreQueDto primaSobreQueDto4= new PrimaSobreQueDto(4L,"Robo","");
		
		lista.add(primaSobreQueDto1);
		lista.add(primaSobreQueDto2);
		lista.add(primaSobreQueDto3);
		lista.add(primaSobreQueDto4);
		
		return lista;
		
	}
}
