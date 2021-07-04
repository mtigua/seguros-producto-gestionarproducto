package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import seguros.producto.gestionarproducto.dto.ConceptoDto;
import seguros.producto.gestionarproducto.entities.Concepto;
import seguros.producto.gestionarproducto.repositories.ConceptoRepository;
import seguros.producto.gestionarproducto.servicesImpl.ConceptoException;
import seguros.producto.gestionarproducto.servicesImpl.ConceptoServiceImpl;

public class ConceptoServiceImplTest {
	
	
	@InjectMocks
	ConceptoServiceImpl conceptoService;
	
	private ConceptoRepository conceptoRepository= Mockito.mock(ConceptoRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(conceptoRepository.findAll()).thenReturn(getLista());
		
		List<ConceptoDto> listaConceptoDto= conceptoService.findAll();
		
		assertEquals(2, listaConceptoDto.size());
	}
	
	@Test(expected = ConceptoException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(conceptoRepository.findAll()).thenThrow(ConceptoException.class);		
		 conceptoService.findAll();
	}
	
	
	private List<Concepto> getLista() {
		List<Concepto> lista= new ArrayList<>();
		
		Concepto Concepto1= createConcepto(1L,"compania","");
		Concepto Concepto2= createConcepto(2L,"negocio","");
		
		lista.add(Concepto1);
		lista.add(Concepto2);
		
		return lista;
		
	}
	
	private Concepto createConcepto(Long id,String nombre,String descripcion) {
		Concepto Concepto= new Concepto();
		Concepto.setId(id);
		Concepto.setDescripcion(descripcion);
		Concepto.setName(nombre);
		return Concepto;
	}

}
