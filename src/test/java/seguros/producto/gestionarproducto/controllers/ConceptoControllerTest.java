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
import seguros.producto.gestionarproducto.controllers.ConceptoController;
import seguros.producto.gestionarproducto.dto.ConceptoDto;
import seguros.producto.gestionarproducto.services.ConceptoService;
import seguros.producto.gestionarproducto.servicesImpl.ConceptoException;

public class ConceptoControllerTest {

	@InjectMocks
	ConceptoController conceptoController;
	
	private ConceptoService conceptoServiceMock= Mockito.mock(ConceptoService.class);
	
	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);
	
	  @Before
	  public void init() {
	       MockitoAnnotations.initMocks(this);
	  }
	  
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testWhenOkGetConcepto() {	
		 List<ConceptoDto> lista= getListaConceptoesDtoMock();		
		 Mockito.when(conceptoServiceMock.findAll()).thenReturn(lista);
		 ResponseEntity<List<ConceptoDto>> response = conceptoController.getConcepto();
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = ConceptoException.class)
	public void testWhenErrorConceptoExceptionGetConcepto() {			
		
		ConceptoException conceptoException = new ConceptoException();
		conceptoException.setErrorMessage("Error en el sistema");
		conceptoException.setConcreteException(new ConceptoException());		 
		conceptoException.setDetail("Error de conexion con la base de datos");
	 		
		 Mockito.when(propertiesMsgMock.getLogger_error_executing_get_concepto()).thenReturn("Error listando conceptos");
		 Mockito.when(conceptoServiceMock.findAll()).thenThrow(conceptoException);
		 conceptoController.getConcepto();	
		 
	}

	
	private List<ConceptoDto> getListaConceptoesDtoMock() {
		List<ConceptoDto> lista= new ArrayList<>();
		
		ConceptoDto conceptoDto1= new ConceptoDto(1L,"compania",null);
		ConceptoDto conceptoDto2= new ConceptoDto(2L,"negocio",null);
		
		lista.add(conceptoDto1);
		lista.add(conceptoDto2);
		
		return lista;
		
	}


}
