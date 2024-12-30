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
import seguros.producto.gestionarproducto.controllers.TipoDescuentoController;
import seguros.producto.gestionarproducto.dto.TipoDescuentoDto;
import seguros.producto.gestionarproducto.services.TipoDescuentoService;
import seguros.producto.gestionarproducto.servicesImpl.TipoDescuentoException;

public class TipoDescuentoControllerTest {


	@InjectMocks
	TipoDescuentoController tipoDescuentoController;
	
	private TipoDescuentoService tipoDescuentoServiceMock= Mockito.mock(TipoDescuentoService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoDescuento() {	
		 List<TipoDescuentoDto> lista= getListaTipoDescuentoDtoMock();		
		 Mockito.when(tipoDescuentoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoDescuentoDto>> response = tipoDescuentoController.getTipoDescuento();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoDescuentoException.class)
	public void testWhenErrorTipoDescuentoExceptionGetTipoDescuento() {			
		
		TipoDescuentoException tipoDescuentoException = new TipoDescuentoException();
		tipoDescuentoException.setErrorMessage("Error en el sistema");
		tipoDescuentoException.setConcreteException(new TipoDescuentoException());		 
		tipoDescuentoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_descuento()).thenReturn("Error listando tipo de descuento");
		 Mockito.when(tipoDescuentoServiceMock.findAll()).thenThrow(tipoDescuentoException);
		 tipoDescuentoController.getTipoDescuento();	
		 
	}

	
	private List<TipoDescuentoDto> getListaTipoDescuentoDtoMock() {
		List<TipoDescuentoDto> lista= new ArrayList<>();
		
		TipoDescuentoDto tipoDescuentoDto1= new TipoDescuentoDto(1L,"Proporcional","");
		TipoDescuentoDto tipoDescuentoDto2= new TipoDescuentoDto(2L,"Sobre cobertura base","");
		
		lista.add(tipoDescuentoDto1);
		lista.add(tipoDescuentoDto2);
		
		return lista;
		
	}

}
