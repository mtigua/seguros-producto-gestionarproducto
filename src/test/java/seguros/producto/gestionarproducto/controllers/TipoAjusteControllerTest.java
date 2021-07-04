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
import seguros.producto.gestionarproducto.controllers.TipoAjusteController;
import seguros.producto.gestionarproducto.dto.TipoAjusteDto;
import seguros.producto.gestionarproducto.services.TipoAjusteService;
import seguros.producto.gestionarproducto.servicesImpl.TipoAjusteException;

public class TipoAjusteControllerTest {
	
	@InjectMocks
	TipoAjusteController tipoAjusteController;
	
	private TipoAjusteService tipoAjusteServiceMock= Mockito.mock(TipoAjusteService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoAjuste() {	
		 List<TipoAjusteDto> lista= getListaTipoAjusteDtoMock();		
		 Mockito.when(tipoAjusteServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoAjusteDto>> response = tipoAjusteController.getTipoAjuste();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoAjusteException.class)
	public void testWhenErrorTipoAjusteExceptionGetTipoAjuste() {			
		
		TipoAjusteException tipoAjusteException = new TipoAjusteException();
		tipoAjusteException.setErrorMessage("Error en el sistema");
		tipoAjusteException.setConcreteException(new TipoAjusteException());		 
		tipoAjusteException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_ajuste()).thenReturn("Error listando tipo de ajuste");
		 Mockito.when(tipoAjusteServiceMock.findAll()).thenThrow(tipoAjusteException);
		 tipoAjusteController.getTipoAjuste();	
		 
	}

	
	private List<TipoAjusteDto> getListaTipoAjusteDtoMock() {
		List<TipoAjusteDto> lista= new ArrayList<>();
		
		TipoAjusteDto tipoAjusteDto1= new TipoAjusteDto(1L,"Afecto","");
		TipoAjusteDto tipoAjusteDto2= new TipoAjusteDto(2L,"Exento","");
		
		lista.add(tipoAjusteDto1);
		lista.add(tipoAjusteDto2);
		
		return lista;
		
	}

}
