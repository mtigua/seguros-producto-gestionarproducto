package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.TipoCoberturaDto;
import seguros.producto.gestionarproducto.entities.TipoCobertura;
import seguros.producto.gestionarproducto.repositories.TipoCoberturaRepository;
import seguros.producto.gestionarproducto.servicesImpl.TipoCoberturaException;
import seguros.producto.gestionarproducto.servicesImpl.TipoCoberturaServiceImpl;

public class TipoCoberturaServiceImplTest {

	@InjectMocks
	TipoCoberturaServiceImpl tipoCoberturaService;
	
	private TipoCoberturaRepository tipoCoberturaRepository= Mockito.mock(TipoCoberturaRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(tipoCoberturaRepository.findAll()).thenReturn(getLista());
		
		List<TipoCoberturaDto> listaTipoCoberturaDto= tipoCoberturaService.findAll();
		
		assertEquals(3, listaTipoCoberturaDto.size());
	}
	
	@Test(expected = TipoCoberturaException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(tipoCoberturaRepository.findAll()).thenThrow(TipoCoberturaException.class);		
		 tipoCoberturaService.findAll();
	}
	
	
	private List<TipoCobertura> getLista() {
		List<TipoCobertura> lista= new ArrayList<>();
		
		TipoCobertura TipoCobertura1= createTipoCobertura(1L,"Tasa","");
		TipoCobertura TipoCobertura2= createTipoCobertura(2L,"Tarifa","");
		TipoCobertura TipoCobertura3= createTipoCobertura(2L,"Tramo","");
		
		lista.add(TipoCobertura1);
		lista.add(TipoCobertura2);
		lista.add(TipoCobertura3);
		
		return lista;
		
	}
	
	private TipoCobertura createTipoCobertura(Long id,String nombre,String descripcion) {
		TipoCobertura TipoCobertura= new TipoCobertura();
		TipoCobertura.setId(id);
		TipoCobertura.setDescripcion(descripcion);
		TipoCobertura.setNombre(nombre);
		return TipoCobertura;
	}

}
