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
import seguros.producto.gestionarproducto.controllers.TipoTraspasoController;
import seguros.producto.gestionarproducto.dto.TipoTraspasoDto;
import seguros.producto.gestionarproducto.services.TipoTraspasoService;
import seguros.producto.gestionarproducto.servicesImpl.TipoTraspasoException;

public class TipoTraspasoControllerTest {
	
	
	@InjectMocks
	TipoTraspasoController tipoTraspasoController;
	
	private TipoTraspasoService tipoTraspasoServiceMock= Mockito.mock(TipoTraspasoService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoTraspaso() {	
		 List<TipoTraspasoDto> lista= getListaTipoTraspasoDtoMock();		
		 Mockito.when(tipoTraspasoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoTraspasoDto>> response = tipoTraspasoController.getTipoTraspaso();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoTraspasoException.class)
	public void testWhenErrorTipoTraspasoExceptionGetTipoTraspaso() {			
		
		TipoTraspasoException tipoTraspasoException = new TipoTraspasoException();
		tipoTraspasoException.setErrorMessage("Error en el sistema");
		tipoTraspasoException.setConcreteException(new TipoTraspasoException());		 
		tipoTraspasoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_traspaso()).thenReturn("Error listando tipo de traspaso");
		 Mockito.when(tipoTraspasoServiceMock.findAll()).thenThrow(tipoTraspasoException);
		 tipoTraspasoController.getTipoTraspaso();	
		 
	}

	
	private List<TipoTraspasoDto> getListaTipoTraspasoDtoMock() {
		List<TipoTraspasoDto> lista= new ArrayList<>();
		
		TipoTraspasoDto tipoTraspasoDto1= new TipoTraspasoDto(1L,"Poliza Colectiva","");
		TipoTraspasoDto tipoTraspasoDto2= new TipoTraspasoDto(2L,"Póliza Individual","");
		
		lista.add(tipoTraspasoDto1);
		lista.add(tipoTraspasoDto2);

		
		return lista;
		
	}

}
