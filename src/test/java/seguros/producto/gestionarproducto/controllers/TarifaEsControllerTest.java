package seguros.producto.gestionarproducto.controllers;

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
import seguros.producto.gestionarproducto.controllers.TarifaEsController;
import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.services.TarifaEsService;
import seguros.producto.gestionarproducto.servicesImpl.TarifaEsException;

public class TarifaEsControllerTest {


	
	@InjectMocks
	TarifaEsController tarifaEsController;
	
	private TarifaEsService tarifaEsServiceMock= Mockito.mock(TarifaEsService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTarifaEs() {	
		 List<TarifaEsDto> lista= getListaTarifaEsDtoMock();		
		 Mockito.when(tarifaEsServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TarifaEsDto>> response = tarifaEsController.getTarifaEs();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TarifaEsException.class)
	public void testWhenErrorTarifaEsExceptionGetTarifaEs() {			
		
		TarifaEsException tarifaEsException = new TarifaEsException();
		tarifaEsException.setErrorMessage("Error en el sistema");
		tarifaEsException.setConcreteException(new TarifaEsException());		 
		tarifaEsException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tarifa_es()).thenReturn("Error listando tarifas");
		 Mockito.when(tarifaEsServiceMock.findAll()).thenThrow(tarifaEsException);
		 tarifaEsController.getTarifaEs();	
		 
	}

	
	private List<TarifaEsDto> getListaTarifaEsDtoMock() {
		List<TarifaEsDto> lista= new ArrayList<>();
		
		TarifaEsDto tarifaEsDto1= new TarifaEsDto(1L,"Valor","");
		TarifaEsDto tarifaEsDto2= new TarifaEsDto(2L,"Tasa","");
		
		lista.add(tarifaEsDto1);
		lista.add(tarifaEsDto2);
		
		return lista;
		
	}

}
