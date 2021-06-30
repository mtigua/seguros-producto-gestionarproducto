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
import seguros.producto.gestionarproducto.controllers.TipoRecargoController;
import seguros.producto.gestionarproducto.dto.TipoRecargoDto;
import seguros.producto.gestionarproducto.services.TipoRecargoService;
import seguros.producto.gestionarproducto.servicesImpl.TipoRecargoException;

public class TipoRecargoControllerTest {


	@InjectMocks
	TipoRecargoController tipoRecargoController;
	
	private TipoRecargoService tipoRecargoServiceMock= Mockito.mock(TipoRecargoService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoRecargo() {	
		 List<TipoRecargoDto> lista= getListaTipoRecargoDtoMock();		
		 Mockito.when(tipoRecargoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoRecargoDto>> response = tipoRecargoController.getTipoRecargo();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoRecargoException.class)
	public void testWhenErrorTipoRecargoExceptionGetTipoRecargo() {			
		
		TipoRecargoException tipoRecargoException = new TipoRecargoException();
		tipoRecargoException.setErrorMessage("Error en el sistema");
		tipoRecargoException.setConcreteException(new TipoRecargoException());		 
		tipoRecargoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_recargo()).thenReturn("Error listando tipo de recargo");
		 Mockito.when(tipoRecargoServiceMock.findAll()).thenThrow(tipoRecargoException);
		 tipoRecargoController.getTipoRecargo();	
		 
	}

	
	private List<TipoRecargoDto> getListaTipoRecargoDtoMock() {
		List<TipoRecargoDto> lista= new ArrayList<>();
		
		TipoRecargoDto tipoRecargoDto1= new TipoRecargoDto(1L,"Proporcional","");
		TipoRecargoDto tipoRecargoDto2= new TipoRecargoDto(2L,"Sobre cobertura base","");
		
		lista.add(tipoRecargoDto1);
		lista.add(tipoRecargoDto2);

		
		return lista;
		
	}


}
