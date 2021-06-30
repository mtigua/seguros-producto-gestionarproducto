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
import seguros.producto.gestionarproducto.controllers.TipoTarifaController;
import seguros.producto.gestionarproducto.dto.TipoTarifaDto;
import seguros.producto.gestionarproducto.services.TipoTarifaService;
import seguros.producto.gestionarproducto.servicesImpl.TipoTarifaException;

public class TipoTarifaControllerTest {

	@InjectMocks
	TipoTarifaController tipoTarifaController;
	
	private TipoTarifaService tipoTarifaServiceMock= Mockito.mock(TipoTarifaService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoTarifa() {	
		 List<TipoTarifaDto> lista= getListaTipoTarifaDtoMock();		
		 Mockito.when(tipoTarifaServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoTarifaDto>> response = tipoTarifaController.getTipoTarifa();
		 assertEquals(4, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoTarifaException.class)
	public void testWhenErrorTipoTarifaExceptionGetTipoTarifa() {			
		
		TipoTarifaException tipoTarifaException = new TipoTarifaException();
		tipoTarifaException.setErrorMessage("Error en el sistema");
		tipoTarifaException.setConcreteException(new TipoTarifaException());		 
		tipoTarifaException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_tarifa()).thenReturn("Error listando tipo de tarifa");
		 Mockito.when(tipoTarifaServiceMock.findAll()).thenThrow(tipoTarifaException);
		 tipoTarifaController.getTipoTarifa();	
		 
	}

	
	private List<TipoTarifaDto> getListaTipoTarifaDtoMock() {
		List<TipoTarifaDto> lista= new ArrayList<>();
		
		TipoTarifaDto tipoTarifaDto1= new TipoTarifaDto(1L,"Por Tramo","");
		TipoTarifaDto tipoTarifaDto2= new TipoTarifaDto(2L,"Fija","");
		TipoTarifaDto tipoTarifaDto3= new TipoTarifaDto(3L,"Por Marca - Modelo - Año","");
		TipoTarifaDto tipoTarifaDto4= new TipoTarifaDto(4L,"Por Tabla Detalle","");
		
		lista.add(tipoTarifaDto1);
		lista.add(tipoTarifaDto2);
		lista.add(tipoTarifaDto3);
		lista.add(tipoTarifaDto4);

		
		return lista;
		
	}

}
