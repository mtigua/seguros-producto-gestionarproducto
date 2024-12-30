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
import seguros.producto.gestionarproducto.controllers.TipoTramoController;
import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.services.TipoTramoService;
import seguros.producto.gestionarproducto.servicesImpl.TipoTramoException;

public class TipoTramoControllerTest {


	@InjectMocks
	TipoTramoController tipoTramoController;
	
	private TipoTramoService tipoTramoServiceMock= Mockito.mock(TipoTramoService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoTramo() {	
		 List<TipoTramoDto> lista= getListaTipoTramoDtoMock();		
		 Mockito.when(tipoTramoServiceMock.findAll(1L)).thenReturn(lista);
		 ResponseEntity<List<TipoTramoDto>> response = tipoTramoController.getTipoTramo(1L);
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoTramoException.class)
	public void testWhenErrorTipoTramoExceptionGetTipoTramo() {			
		
		TipoTramoException tipoTramoException = new TipoTramoException();
		tipoTramoException.setErrorMessage("Error en el sistema");
		tipoTramoException.setConcreteException(new TipoTramoException());		 
		tipoTramoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_tramo()).thenReturn("Error listando tipo de tramo");
		 Mockito.when(tipoTramoServiceMock.findAll(1L)).thenThrow(tipoTramoException);
		 tipoTramoController.getTipoTramo(1L);	
		 
	}

	
	private List<TipoTramoDto> getListaTipoTramoDtoMock() {
		List<TipoTramoDto> lista= new ArrayList<>();
		
		TipoTramoDto tipoTramoDto1= new TipoTramoDto(1L,"Edad","");
		TipoTramoDto tipoTramoDto2= new TipoTramoDto(2L,"Monto","");
		
		lista.add(tipoTramoDto1);
		lista.add(tipoTramoDto2);

		
		return lista;
		
	}

}
