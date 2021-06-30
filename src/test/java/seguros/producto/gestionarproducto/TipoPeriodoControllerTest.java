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
import seguros.producto.gestionarproducto.controllers.TipoPeriodoController;
import seguros.producto.gestionarproducto.dto.TipoPeriodoDto;
import seguros.producto.gestionarproducto.services.TipoPeriodoService;
import seguros.producto.gestionarproducto.servicesImpl.TipoPeriodoException;

public class TipoPeriodoControllerTest {

	
	@InjectMocks
	TipoPeriodoController tipoPeriodoController;
	
	private TipoPeriodoService tipoPeriodoServiceMock= Mockito.mock(TipoPeriodoService.class);
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetTipoPeriodo() {	
		 List<TipoPeriodoDto> lista= getListaTipoPeriodoDtoMock();		
		 Mockito.when(tipoPeriodoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<TipoPeriodoDto>> response = tipoPeriodoController.getTipoPeriodo();
		 assertEquals(6, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = TipoPeriodoException.class)
	public void testWhenErrorTipoPeriodoExceptionGetTipoPeriodo() {			
		
		TipoPeriodoException tipoPeriodoException = new TipoPeriodoException();
		tipoPeriodoException.setErrorMessage("Error en el sistema");
		tipoPeriodoException.setConcreteException(new TipoPeriodoException());		 
		tipoPeriodoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tipo_periodo()).thenReturn("Error listando tipo de periodo");
		 Mockito.when(tipoPeriodoServiceMock.findAll()).thenThrow(tipoPeriodoException);
		 tipoPeriodoController.getTipoPeriodo();	
		 
	}

	
	private List<TipoPeriodoDto> getListaTipoPeriodoDtoMock() {
		List<TipoPeriodoDto> lista= new ArrayList<>();
		
		TipoPeriodoDto tipoPeriodoDto1= new TipoPeriodoDto(1L,"Cuotas","");
		TipoPeriodoDto tipoPeriodoDto2= new TipoPeriodoDto(2L,"Anual","");
		TipoPeriodoDto tipoPeriodoDto3= new TipoPeriodoDto(3L,"Semestral","");
		TipoPeriodoDto tipoPeriodoDto4= new TipoPeriodoDto(4L,"Trimestral","");
		TipoPeriodoDto tipoPeriodoDto5= new TipoPeriodoDto(5L,"Bimensual","");
		TipoPeriodoDto tipoPeriodoDto6= new TipoPeriodoDto(6L,"Mensual","");
		
		lista.add(tipoPeriodoDto1);
		lista.add(tipoPeriodoDto2);
		lista.add(tipoPeriodoDto3);
		lista.add(tipoPeriodoDto4);
		lista.add(tipoPeriodoDto5);
		lista.add(tipoPeriodoDto6);
		
		return lista;
		
	}

}
