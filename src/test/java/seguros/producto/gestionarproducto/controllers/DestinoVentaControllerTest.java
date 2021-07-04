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
import seguros.producto.gestionarproducto.controllers.DestinoVentaController;
import seguros.producto.gestionarproducto.dto.DestinoVentaDto;
import seguros.producto.gestionarproducto.services.DestinoVentaService;
import seguros.producto.gestionarproducto.servicesImpl.DestinoVentaException;

public class DestinoVentaControllerTest {

	
	@InjectMocks
	DestinoVentaController destinoVentaController;
	
	private DestinoVentaService destinoVentaServiceMock= Mockito.mock(DestinoVentaService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetDestinoVenta() {	
		 List<DestinoVentaDto> lista= getListaDestinoVentaDtoMock();		
		 Mockito.when(destinoVentaServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<DestinoVentaDto>> response = destinoVentaController.getDestinoVentas();
		 assertEquals(3, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = DestinoVentaException.class)
	public void testWhenErrorDestinoVentaExceptionGetDestinoVenta() {			
		
		DestinoVentaException destinoVentaException = new DestinoVentaException();
		destinoVentaException.setErrorMessage("Error en el sistema");
		destinoVentaException.setConcreteException(new DestinoVentaException());		 
		destinoVentaException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_destino_venta()).thenReturn("Error listando destino de ventas");
		 Mockito.when(destinoVentaServiceMock.findAll()).thenThrow(destinoVentaException);
		 destinoVentaController.getDestinoVentas();	
		 
	}

	
	private List<DestinoVentaDto> getListaDestinoVentaDtoMock() {
		List<DestinoVentaDto> lista= new ArrayList<>();
		
		DestinoVentaDto destinoVentaDto1= new DestinoVentaDto(1L,"Venta solo a Titulares","");
		DestinoVentaDto destinoVentaDto2= new DestinoVentaDto(2L,"Venta solo a Adicionales","");
		DestinoVentaDto destinoVentaDto3= new DestinoVentaDto(3L,"Venta a Todos","");
		
		lista.add(destinoVentaDto1);
		lista.add(destinoVentaDto2);
		lista.add(destinoVentaDto3);
		
		return lista;
		
	}

}
