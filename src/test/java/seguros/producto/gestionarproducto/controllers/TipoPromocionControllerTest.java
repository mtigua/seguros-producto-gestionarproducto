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
import seguros.producto.gestionarproducto.controllers.TipoPromocionController;
import seguros.producto.gestionarproducto.dto.TipoPromocionDto;
import seguros.producto.gestionarproducto.services.TipoPromocionService;
import seguros.producto.gestionarproducto.servicesImpl.TipoPromocionException;

public class TipoPromocionControllerTest {


	@InjectMocks
	TipoPromocionController tipoPromocionController;
	
	private TipoPromocionService tipoPromocionServiceMock= Mockito.mock(TipoPromocionService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoPromocion() {	
		 List<TipoPromocionDto> lista= getListaTipoPromocionDtoMock();		
		 Mockito.when(tipoPromocionServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoPromocionDto>> response = tipoPromocionController.getTipoPromocion();
		 assertEquals(3, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoPromocionException.class)
	public void testWhenErrorTipoPromocionExceptionGetTipoPromocion() {			
		
		TipoPromocionException tipoPromocionException = new TipoPromocionException();
		tipoPromocionException.setErrorMessage("Error en el sistema");
		tipoPromocionException.setConcreteException(new TipoPromocionException());		 
		tipoPromocionException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_promocion()).thenReturn("Error listando tipo de promocion");
		 Mockito.when(tipoPromocionServiceMock.findAll()).thenThrow(tipoPromocionException);
		 tipoPromocionController.getTipoPromocion();	
		 
	}

	
	private List<TipoPromocionDto> getListaTipoPromocionDtoMock() {
		List<TipoPromocionDto> lista= new ArrayList<>();
		
		TipoPromocionDto tipoPromocionDto1= new TipoPromocionDto(1L,"Gift-Card","");
		TipoPromocionDto tipoPromocionDto2= new TipoPromocionDto(2L,"Puntos Nectar","");
		TipoPromocionDto tipoPromocionDto3= new TipoPromocionDto(3L,"Producto","");
		
		lista.add(tipoPromocionDto1);
		lista.add(tipoPromocionDto2);
		lista.add(tipoPromocionDto3);
		
		return lista;
		
	}


}
