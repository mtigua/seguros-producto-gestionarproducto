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
import seguros.producto.gestionarproducto.controllers.TipoSeguroController;
import seguros.producto.gestionarproducto.dto.TipoSeguroDto;
import seguros.producto.gestionarproducto.services.TipoSeguroService;
import seguros.producto.gestionarproducto.servicesImpl.TipoSeguroException;

public class TipoSeguroControllerTest {

	@InjectMocks
	TipoSeguroController tipoSeguroController;
	
	private TipoSeguroService tipoSeguroServiceMock= Mockito.mock(TipoSeguroService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoSeguro() {	
		 List<TipoSeguroDto> lista= getListaTipoSeguroDtoMock();		
		 Mockito.when(tipoSeguroServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoSeguroDto>> response = tipoSeguroController.getTipoSeguro();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoSeguroException.class)
	public void testWhenErrorTipoSeguroExceptionGetTipoSeguro() {			
		
		TipoSeguroException tipoSeguroException = new TipoSeguroException();
		tipoSeguroException.setErrorMessage("Error en el sistema");
		tipoSeguroException.setConcreteException(new TipoSeguroException());		 
		tipoSeguroException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_seguro()).thenReturn("Error listando tipo de seguro");
		 Mockito.when(tipoSeguroServiceMock.findAll()).thenThrow(tipoSeguroException);
		 tipoSeguroController.getTipoSeguro();	
		 
	}

	
	private List<TipoSeguroDto> getListaTipoSeguroDtoMock() {
		List<TipoSeguroDto> lista= new ArrayList<>();
		
		TipoSeguroDto tipoSeguroDto1= new TipoSeguroDto(1L,"Financiero","");
		TipoSeguroDto tipoSeguroDto2= new TipoSeguroDto(2L,"Tradicional","");
		
		lista.add(tipoSeguroDto1);
		lista.add(tipoSeguroDto2);

		
		return lista;
		
	}

}
