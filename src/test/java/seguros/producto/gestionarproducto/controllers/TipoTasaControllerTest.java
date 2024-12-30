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
import seguros.producto.gestionarproducto.controllers.TipoTasaController;
import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.services.TipoTasaService;
import seguros.producto.gestionarproducto.servicesImpl.TipoTasaException;

public class TipoTasaControllerTest {

	@InjectMocks
	TipoTasaController tipoTasaController;
	
	private TipoTasaService tipoTasaServiceMock= Mockito.mock(TipoTasaService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoTasa() {	
		 List<TipoTasaDto> lista= getListaTipoTasaDtoMock();		
		 Mockito.when(tipoTasaServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoTasaDto>> response = tipoTasaController.getTipoTasa();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoTasaException.class)
	public void testWhenErrorTipoTasaExceptionGetTipoTasa() {			
		
		TipoTasaException tipoTasaException = new TipoTasaException();
		tipoTasaException.setErrorMessage("Error en el sistema");
		tipoTasaException.setConcreteException(new TipoTasaException());		 
		tipoTasaException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_tasa()).thenReturn("Error listando tipo de tasa");
		 Mockito.when(tipoTasaServiceMock.findAll()).thenThrow(tipoTasaException);
		 tipoTasaController.getTipoTasa();	
		 
	}

	
	private List<TipoTasaDto> getListaTipoTasaDtoMock() {
		List<TipoTasaDto> lista= new ArrayList<>();
		
		TipoTasaDto tipoTasaDto1= new TipoTasaDto(1L,"%","");
		TipoTasaDto tipoTasaDto2= new TipoTasaDto(2L,"xM","");
		
		lista.add(tipoTasaDto1);
		lista.add(tipoTasaDto2);

		
		return lista;
		
	}


}
