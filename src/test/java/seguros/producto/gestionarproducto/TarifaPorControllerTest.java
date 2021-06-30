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
import seguros.producto.gestionarproducto.controllers.TarifaPorController;
import seguros.producto.gestionarproducto.dto.TarifaPorDto;
import seguros.producto.gestionarproducto.services.TarifaPorService;
import seguros.producto.gestionarproducto.servicesImpl.TarifaPorException;

public class TarifaPorControllerTest {


	
	@InjectMocks
	TarifaPorController tarifaPorController;
	
	private TarifaPorService tarifaPorServiceMock= Mockito.mock(TarifaPorService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTarifaPor() {	
		 List<TarifaPorDto> lista= getListaTarifaPorDtoMock();		
		 Mockito.when(tarifaPorServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TarifaPorDto>> response = tarifaPorController.getTarifaPor();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TarifaPorException.class)
	public void testWhenErrorTarifaPorExceptionGetTarifaPor() {			
		
		TarifaPorException tarifaPorException = new TarifaPorException();
		tarifaPorException.setErrorMessage("Error en el sistema");
		tarifaPorException.setConcreteException(new TarifaPorException());		 
		tarifaPorException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tarifa_por()).thenReturn("Error listando tarifa por");
		 Mockito.when(tarifaPorServiceMock.findAll()).thenThrow(tarifaPorException);
		 tarifaPorController.getTarifaPor();	
		 
	}

	
	private List<TarifaPorDto> getListaTarifaPorDtoMock() {
		List<TarifaPorDto> lista= new ArrayList<>();
		
		TarifaPorDto tarifaPorDto1= new TarifaPorDto(1L,"Por Plan","");
		TarifaPorDto tarifaPorDto2= new TarifaPorDto(2L,"Por Coberturas","");
		
		lista.add(tarifaPorDto1);
		lista.add(tarifaPorDto2);
		
		return lista;
		
	}
}
