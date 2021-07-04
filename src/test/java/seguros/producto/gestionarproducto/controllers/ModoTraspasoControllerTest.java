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
import seguros.producto.gestionarproducto.controllers.ModoTraspasoController;
import seguros.producto.gestionarproducto.dto.ModoTraspasoDto;
import seguros.producto.gestionarproducto.services.ModoTraspasoService;
import seguros.producto.gestionarproducto.servicesImpl.ModoTraspasoException;

public class ModoTraspasoControllerTest {


	
	@InjectMocks
	ModoTraspasoController modoTraspasoController;
	
	private ModoTraspasoService modoTraspasoServiceMock= Mockito.mock(ModoTraspasoService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetModoTraspaso() {	
		 List<ModoTraspasoDto> lista= getListaModoTraspasoDtoMock();		
		 Mockito.when(modoTraspasoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<ModoTraspasoDto>> response = modoTraspasoController.getModoTraspaso();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = ModoTraspasoException.class)
	public void testWhenErrorModoTraspasoExceptionGetModoTraspaso() {			
		
		ModoTraspasoException modoTraspasoException = new ModoTraspasoException();
		modoTraspasoException.setErrorMessage("Error en el sistema");
		modoTraspasoException.setConcreteException(new ModoTraspasoException());		 
		modoTraspasoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_modo_traspaso()).thenReturn("Error listando modo de traspaso");
		 Mockito.when(modoTraspasoServiceMock.findAll()).thenThrow(modoTraspasoException);
		 modoTraspasoController.getModoTraspaso();	
		 
	}

	
	private List<ModoTraspasoDto> getListaModoTraspasoDtoMock() {
		List<ModoTraspasoDto> lista= new ArrayList<>();
		
		ModoTraspasoDto modoTraspasoDto1= new ModoTraspasoDto(1L,"Identificación fijo","");
		ModoTraspasoDto modoTraspasoDto2= new ModoTraspasoDto(2L,"Identificación libre","");
		
		lista.add(modoTraspasoDto1);
		lista.add(modoTraspasoDto2);
		
		return lista;
		
	}

}
