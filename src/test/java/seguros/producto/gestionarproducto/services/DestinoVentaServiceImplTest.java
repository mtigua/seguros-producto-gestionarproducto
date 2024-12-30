package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import seguros.producto.gestionarproducto.dto.DestinoVentaDto;
import seguros.producto.gestionarproducto.entities.DestinoVenta;
import seguros.producto.gestionarproducto.repositories.DestinoVentaRepository;
import seguros.producto.gestionarproducto.servicesImpl.DestinoVentaException;
import seguros.producto.gestionarproducto.servicesImpl.DestinoVentaServiceImpl;

public class DestinoVentaServiceImplTest {

	@InjectMocks
	DestinoVentaServiceImpl DestinoVentaService;
	
	private DestinoVentaRepository DestinoVentaRepository= Mockito.mock(DestinoVentaRepository.class);
	
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenOkFindAll() {
		Mockito.when(DestinoVentaRepository.findAll()).thenReturn(getLista());
		
		List<DestinoVentaDto> listaDestinoVentaDto= DestinoVentaService.findAll();
		
		assertEquals(3, listaDestinoVentaDto.size());
	}
	
	@Test(expected = DestinoVentaException.class)
	public void testWhenError500FindAll() {
		 Mockito.when(DestinoVentaRepository.findAll()).thenThrow(DestinoVentaException.class);		
		 DestinoVentaService.findAll();
	}
	
	
	private List<DestinoVenta> getLista() {
		List<DestinoVenta> lista= new ArrayList<>();
		
		DestinoVenta DestinoVenta1= createDestinoVenta(1L,"Venta solo a Titulares","");
		DestinoVenta DestinoVenta2= createDestinoVenta(2L,"Venta solo a Adicionales","");
		DestinoVenta DestinoVenta3= createDestinoVenta(3L,"Venta a Todos","");
		
		lista.add(DestinoVenta1);
		lista.add(DestinoVenta2);
		lista.add(DestinoVenta3);
		
		return lista;
		
	}
	
	private DestinoVenta createDestinoVenta(Long id,String nombre,String descripcion) {
		DestinoVenta DestinoVenta= new DestinoVenta();
		DestinoVenta.setId(id);
		DestinoVenta.setDescripcion(descripcion);
		DestinoVenta.setNombre(nombre);
		return DestinoVenta;
	}

}
