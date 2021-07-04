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
import seguros.producto.gestionarproducto.controllers.TipoCoberturaController;
import seguros.producto.gestionarproducto.dto.TipoCoberturaDto;
import seguros.producto.gestionarproducto.services.TipoCoberturaService;
import seguros.producto.gestionarproducto.servicesImpl.TipoCoberturaException;

public class TipoCoberturaControllerTest {
	
	
	@InjectMocks
	TipoCoberturaController tipoCoberturaController;
	
	private TipoCoberturaService tipoCoberturaServiceMock= Mockito.mock(TipoCoberturaService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoCobertura() {	
		 List<TipoCoberturaDto> lista= getListaTipoCoberturaDtoMock();		
		 Mockito.when(tipoCoberturaServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoCoberturaDto>> response = tipoCoberturaController.getTipoCobertura();
		 assertEquals(3, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoCoberturaException.class)
	public void testWhenErrorTipoCoberturaExceptionGetTipoCobertura() {			
		
		TipoCoberturaException tipoCoberturaException = new TipoCoberturaException();
		tipoCoberturaException.setErrorMessage("Error en el sistema");
		tipoCoberturaException.setConcreteException(new TipoCoberturaException());		 
		tipoCoberturaException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_cobertura()).thenReturn("Error listando tipo de cobertura");
		 Mockito.when(tipoCoberturaServiceMock.findAll()).thenThrow(tipoCoberturaException);
		 tipoCoberturaController.getTipoCobertura();	
		 
	}

	
	private List<TipoCoberturaDto> getListaTipoCoberturaDtoMock() {
		List<TipoCoberturaDto> lista= new ArrayList<>();
		
		TipoCoberturaDto tipoCoberturaDto1= new TipoCoberturaDto(1L,"Tasa","");
		TipoCoberturaDto tipoCoberturaDto2= new TipoCoberturaDto(2L,"Tarifa","");
		TipoCoberturaDto tipoCoberturaDto3= new TipoCoberturaDto(3L,"Tramo","");
		
		lista.add(tipoCoberturaDto1);
		lista.add(tipoCoberturaDto2);
		lista.add(tipoCoberturaDto3);
		
		return lista;
		
	}

}
