package seguros.producto.gestionarproducto.services;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.entities.TerminoCorto;
import seguros.producto.gestionarproducto.entities.TipoMulta;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.repositories.ProductoRepository;
import seguros.producto.gestionarproducto.repositories.TerminoCortoRepository;
import seguros.producto.gestionarproducto.repositories.TipoMultaRepository;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoServiceImpl;


public class ProductoServiceImplTest {

	@InjectMocks
	ProductoServiceImpl productoService;
	
	private ProductoRepository productoRepository= Mockito.mock(ProductoRepository.class);
	
	private TipoMultaRepository tipoMultaRepository= Mockito.mock(TipoMultaRepository.class);
	
	private TerminoCortoRepository terminoCortoRepository= Mockito.mock(TerminoCortoRepository.class);
	
	@Before
	public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetWhenOkTerminosCortosByProduct() {
		
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(true);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCorto terminoCorto1= createTerminoCorto(1L, 1, 4, null, null, 4, "PESA",tipoMulta);
		TerminoCorto terminoCorto2= createTerminoCorto(2L, 4, 7, null, null, 9, "PESA",tipoMulta);
		
		Set<TerminoCorto> listaTerminosCortos= new HashSet<>();
		listaTerminosCortos.add(terminoCorto1);
		listaTerminosCortos.add(terminoCorto2);
		
		producto.setTerminosCortos(listaTerminosCortos);
		
		Optional<Producto> productoO = Optional.of(producto);
		Mockito.when(productoRepository.findById(1L)).thenReturn(productoO);
		
		List<TerminoCortoDto> listaTerminoCortoDto= productoService.getTerminosCortosByProduct(1L);
		assertEquals(2, listaTerminoCortoDto.size());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testGetWhenErrorTerminosCortosByProduct() {		
		Mockito.when(productoRepository.findById(1L)).thenThrow(ResourceNotFoundException.class);		
		productoService.getTerminosCortosByProduct(1L);
	}
	
	@Test(expected = ForbiddenException.class)
	public void testGetWhenError403TerminosCortosByProduct() {		
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(false);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCorto terminoCorto1= createTerminoCorto(1L, 1, 4, null, null, 4, "PESA",tipoMulta);
		TerminoCorto terminoCorto2= createTerminoCorto(2L, 4, 7, null, null, 9, "PESA",tipoMulta);
		
		Set<TerminoCorto> listaTerminosCortos= new HashSet<>();
		listaTerminosCortos.add(terminoCorto1);
		listaTerminosCortos.add(terminoCorto2);
		
		producto.setTerminosCortos(listaTerminosCortos);
		
		Optional<Producto> productoO = Optional.of(producto);
		Mockito.when(productoRepository.findById(1L)).thenReturn(productoO);
		
		 productoService.getTerminosCortosByProduct(1L);
	}
	
	@Test(expected = ProductoException.class)
	public void testGetWhenError500TerminosCortosByProduct() {		
		Mockito.when(productoRepository.findById(1L)).thenThrow(ProductoException.class);		
		productoService.getTerminosCortosByProduct(1L);
	}
	

	@Test
	public void testSaveWhenOkTerminosCortosByProduct() {
		
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(true);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCortoSaveDto terminoCortoSaveDto1= createTerminoCortoSaveDto(1L, 1, 4, null, null, 4, "PESA",1L);
		TerminoCortoSaveDto terminoCortoSaveDto2= createTerminoCortoSaveDto(2L, 4, 7, null, null, 9, "PESA",1L);
		
		List<TerminoCortoSaveDto> listaTerminosCortos= new ArrayList<>();
		listaTerminosCortos.add(terminoCortoSaveDto1);
		listaTerminosCortos.add(terminoCortoSaveDto2);		
		
		producto.setTerminosCortos(new HashSet<TerminoCorto>());
		Optional<Producto> productoO = Optional.of(producto);
		Mockito.when(productoRepository.findById(1L)).thenReturn(productoO);
		Mockito.when(tipoMultaRepository.getOne(1L)).thenReturn(tipoMulta);
		
		 productoService.saveTerminosCortosByProduct(1L,listaTerminosCortos);
		
		 Mockito.verify(productoRepository).save(producto ) ;
		
	}
	
	@Test(expected = ForbiddenException.class)
	public void testSaveWhenError403TerminosCortosByProduct() {
		
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(false);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCortoSaveDto terminoCortoSaveDto1= createTerminoCortoSaveDto(1L, 1, 4, null, null, 4, "PESA",1L);
		TerminoCortoSaveDto terminoCortoSaveDto2= createTerminoCortoSaveDto(2L, 4, 7, null, null, 9, "PESA",1L);
		
		List<TerminoCortoSaveDto> listaTerminosCortos= new ArrayList<>();
		listaTerminosCortos.add(terminoCortoSaveDto1);
		listaTerminosCortos.add(terminoCortoSaveDto2);		
		
		producto.setTerminosCortos(new HashSet<TerminoCorto>());
		Optional<Producto> productoO = Optional.of(producto);
		Mockito.when(productoRepository.findById(1L)).thenReturn(productoO);
		Mockito.when(tipoMultaRepository.getOne(1L)).thenReturn(tipoMulta);
		
		 productoService.saveTerminosCortosByProduct(1L,listaTerminosCortos);		
		
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testSaveWhenError404TerminosCortosByProduct() {
		
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(false);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCortoSaveDto terminoCortoSaveDto1= createTerminoCortoSaveDto(1L, 1, 4, null, null, 4, "PESA",1L);
		TerminoCortoSaveDto terminoCortoSaveDto2= createTerminoCortoSaveDto(2L, 4, 7, null, null, 9, "PESA",1L);
		
		List<TerminoCortoSaveDto> listaTerminosCortos= new ArrayList<>();
		listaTerminosCortos.add(terminoCortoSaveDto1);
		listaTerminosCortos.add(terminoCortoSaveDto2);		
		
		producto.setTerminosCortos(new HashSet<TerminoCorto>());
		Mockito.when(productoRepository.findById(1L)).thenThrow(ResourceNotFoundException.class);
		Mockito.when(tipoMultaRepository.getOne(1L)).thenReturn(tipoMulta);
		
		 productoService.saveTerminosCortosByProduct(1L,listaTerminosCortos);		
		
	}
	
	@Test(expected = ProductoException.class)
	public void testSaveWhenError500TerminosCortosByProduct() {
		
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(false);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCortoSaveDto terminoCortoSaveDto1= createTerminoCortoSaveDto(1L, 1, 4, null, null, 4, "PESA",1L);
		TerminoCortoSaveDto terminoCortoSaveDto2= createTerminoCortoSaveDto(2L, 4, 7, null, null, 9, "PESA",1L);
		
		List<TerminoCortoSaveDto> listaTerminosCortos= new ArrayList<>();
		listaTerminosCortos.add(terminoCortoSaveDto1);
		listaTerminosCortos.add(terminoCortoSaveDto2);		
		
		producto.setTerminosCortos(new HashSet<TerminoCorto>());
		Mockito.when(productoRepository.findById(1L)).thenThrow(ProductoException.class);
		Mockito.when(tipoMultaRepository.getOne(1L)).thenReturn(tipoMulta);
		
		 productoService.saveTerminosCortosByProduct(1L,listaTerminosCortos);		
		
	}
	

	@Test
	public void testDeleteWhenOkTerminosCortosByProduct() {
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(true);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCorto terminoCortoSaveDto1= createTerminoCorto(1L, 1, 4, null, null, 4, "PESA",tipoMulta);
		TerminoCorto terminoCortoSaveDto2= createTerminoCorto(2L, 4, 7, null, null, 9, "PESA",tipoMulta);		
		
		Set<TerminoCorto> listaTerminoCorto= new HashSet<>();
		listaTerminoCorto.add(terminoCortoSaveDto1);
		listaTerminoCorto.add(terminoCortoSaveDto2);
		
		producto.setTerminosCortos(listaTerminoCorto);
		
		
		Optional<Producto> productoO = Optional.of(producto);
		Optional<TerminoCorto> terminoCortoO= Optional.of(terminoCortoSaveDto1);
		
		Mockito.when(productoRepository.findById(1L)).thenReturn(productoO);
		Mockito.when(terminoCortoRepository.findById(1L)).thenReturn(terminoCortoO);
		
		 productoService.deleteTerminosCortosByProduct(1L, 1L);
		 
		 assertEquals(1, productoO.get().getTerminosCortos().size());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteWhenError404TerminosCortosByProduct() {
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(true);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCorto terminoCortoSaveDto1= createTerminoCorto(1L, 1, 4, null, null, 4, "PESA",tipoMulta);
		TerminoCorto terminoCortoSaveDto2= createTerminoCorto(2L, 4, 7, null, null, 9, "PESA",tipoMulta);		
		
		Set<TerminoCorto> listaTerminoCorto= new HashSet<>();
		listaTerminoCorto.add(terminoCortoSaveDto1);
		listaTerminoCorto.add(terminoCortoSaveDto2);
		
		producto.setTerminosCortos(listaTerminoCorto);		
		
		Optional<TerminoCorto> terminoCortoO= Optional.of(terminoCortoSaveDto1);
		
		Mockito.when(productoRepository.findById(1L)).thenThrow(ResourceNotFoundException.class);
		Mockito.when(terminoCortoRepository.findById(1L)).thenReturn(terminoCortoO);
		
		productoService.deleteTerminosCortosByProduct(1L, 1L);
		 
	}
	
	@Test(expected = ForbiddenException.class)
	public void testDeleteWhenError403TerminosCortosByProduct() {
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(false);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCorto terminoCortoSaveDto1= createTerminoCorto(1L, 1, 4, null, null, 4, "PESA",tipoMulta);
		TerminoCorto terminoCortoSaveDto2= createTerminoCorto(2L, 4, 7, null, null, 9, "PESA",tipoMulta);		
		
		Set<TerminoCorto> listaTerminoCorto= new HashSet<>();
		listaTerminoCorto.add(terminoCortoSaveDto1);
		listaTerminoCorto.add(terminoCortoSaveDto2);
		
		producto.setTerminosCortos(listaTerminoCorto);		
		
		Optional<Producto> productoO = Optional.of(producto);
		Optional<TerminoCorto> terminoCortoO= Optional.of(terminoCortoSaveDto1);
		
		Mockito.when(productoRepository.findById(1L)).thenReturn(productoO);
		Mockito.when(terminoCortoRepository.findById(1L)).thenReturn(terminoCortoO);
		
		productoService.deleteTerminosCortosByProduct(1L, 1L);
		 
	}
	
	@Test(expected = ProductoException.class)
	public void testDeleteWhenError500TerminosCortosByProduct() {
		Producto producto =new Producto();
		producto.setId(1L);
		producto.setAplicaTc(false);
		
		TipoMulta tipoMulta= createTipoMulta(1L,"Cuotas","");
		
		TerminoCorto terminoCortoSaveDto1= createTerminoCorto(1L, 1, 4, null, null, 4, "PESA",tipoMulta);
		TerminoCorto terminoCortoSaveDto2= createTerminoCorto(2L, 4, 7, null, null, 9, "PESA",tipoMulta);		
		
		Set<TerminoCorto> listaTerminoCorto= new HashSet<>();
		listaTerminoCorto.add(terminoCortoSaveDto1);
		listaTerminoCorto.add(terminoCortoSaveDto2);
		
		producto.setTerminosCortos(listaTerminoCorto);		
		
		Optional<TerminoCorto> terminoCortoO= Optional.of(terminoCortoSaveDto1);
		
		Mockito.when(productoRepository.findById(1L)).thenThrow(ProductoException.class);
		Mockito.when(terminoCortoRepository.findById(1L)).thenReturn(terminoCortoO);
		
		productoService.deleteTerminosCortosByProduct(1L, 1L);
		 
	}

//	@Test
//	public void testUpdateTerminosCortosByProduct() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetInfoProducto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTramosByProduct() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSaveTramosByProduct() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteTramoByProduct() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateTramoByProduct() {
//		fail("Not yet implemented");
//	}
	
	
	private TipoMulta createTipoMulta(Long id, String nombre, String descripcion) {
		TipoMulta tipoMulta= new TipoMulta();
		tipoMulta.setId(id);
		tipoMulta.setNombre(nombre);
		tipoMulta.setDescripcion(descripcion);
		return tipoMulta;
	}
	
	private TerminoCorto createTerminoCorto(Long id, Integer mesDesde, Integer mesHasta, BigDecimal primaPeriodo, BigDecimal monto, Integer cuotas, String moneda, TipoMulta tipoMulta) {
		TerminoCorto terminoCorto= new TerminoCorto();
		terminoCorto.setId(id);
		terminoCorto.setCuotas(cuotas);
		terminoCorto.setMesDesde(mesDesde);
		terminoCorto.setMesHasta(mesHasta);
		terminoCorto.setMoneda(moneda);
		terminoCorto.setMonto(monto);
		terminoCorto.setPrimaPeriodo(primaPeriodo);
		terminoCorto.setTipoMulta(tipoMulta);
		
		return terminoCorto;		
		
	}
	
	private TerminoCortoSaveDto createTerminoCortoSaveDto(Long id, Integer mesDesde, Integer mesHasta, BigDecimal primaPeriodo, BigDecimal monto, Integer cuotas, String moneda, Long tipoMulta) {
		TerminoCortoSaveDto terminoCortoSaveDto= new TerminoCortoSaveDto();
		terminoCortoSaveDto.setId(id);
		terminoCortoSaveDto.setCuotas(cuotas);
		terminoCortoSaveDto.setMesDesde(mesDesde);
		terminoCortoSaveDto.setMesHasta(mesHasta);
		terminoCortoSaveDto.setMoneda(moneda);
		terminoCortoSaveDto.setMonto(monto);
		terminoCortoSaveDto.setPrimaPeriodo(primaPeriodo);
		terminoCortoSaveDto.setTipoMulta(tipoMulta);
		
		return terminoCortoSaveDto;		
		
	}

}
