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
import seguros.producto.gestionarproducto.controllers.TipoMultaController;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.services.TipoMultaService;
import seguros.producto.gestionarproducto.servicesImpl.TipoMultaException;

public class TipoMultaControllerTest {

	
	@InjectMocks
	TipoMultaController tipoMultaController;
	
	private TipoMultaService tipoMultaServiceMock= Mockito.mock(TipoMultaService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoMulta() {	
		 List<TipoMultaDto> lista= getListaTipoMultaDtoMock();		
		 Mockito.when(tipoMultaServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoMultaDto>> response = tipoMultaController.getTipoMulta();
		 assertEquals(3, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoMultaException.class)
	public void testWhenErrorTipoMultaExceptionGetTipoMulta() {			
		
		TipoMultaException tipoMultaException = new TipoMultaException();
		tipoMultaException.setErrorMessage("Error en el sistema");
		tipoMultaException.setConcreteException(new TipoMultaException());		 
		tipoMultaException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_multa()).thenReturn("Error listando tipo de multa");
		 Mockito.when(tipoMultaServiceMock.findAll()).thenThrow(tipoMultaException);
		 tipoMultaController.getTipoMulta();	
		 
	}

	
	private List<TipoMultaDto> getListaTipoMultaDtoMock() {
		List<TipoMultaDto> lista= new ArrayList<>();
		
		TipoMultaDto tipoMultaDto1= new TipoMultaDto(1L,"Monto","");
		TipoMultaDto tipoMultaDto2= new TipoMultaDto(2L,"Tabla","");
		TipoMultaDto tipoMultaDto3= new TipoMultaDto(3L,"Cuotas","");
		
		lista.add(tipoMultaDto1);
		lista.add(tipoMultaDto2);
		lista.add(tipoMultaDto3);
		
		return lista;
		
	}

}
