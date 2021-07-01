package seguros.producto.gestionarproducto.controllers;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.controllers.ProductoController;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.PrimaSobreQueDto;
import seguros.producto.gestionarproducto.dto.ProductoPageDto;
import seguros.producto.gestionarproducto.dto.TarifaEsDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.dto.TipoMultaDto;
import seguros.producto.gestionarproducto.dto.TipoRamoDto;
import seguros.producto.gestionarproducto.dto.TipoTasaDto;
import seguros.producto.gestionarproducto.dto.TipoTramoDto;
import seguros.producto.gestionarproducto.dto.TramoDto;
import seguros.producto.gestionarproducto.dto.TramoListDto;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.services.ProductoService;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;



public class ProductoControllerTest {
	
	@InjectMocks
	ProductoController productoController;
	
    private ProductoService productoService= Mockito.mock(ProductoService.class);	

	private PropertiesMsg propertiesMsgMock = Mockito.mock(PropertiesMsg.class);

	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_get_terminos_corto_by_product()).thenReturn("Error listando terminos cortos dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_save_terminos_corto_by_product()).thenReturn("Error registrando termino corto dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_update_terminos_corto_by_product()).thenReturn("Error actualizando termino corto dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_delete_terminos_corto_by_product()).thenReturn("Error eliminando termino corto dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_get_tramo_by_product()).thenReturn("Error listando tramos dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_save_tramo_by_product()).thenReturn("Error registrando tramo dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_update_tramo_by_product()).thenReturn("Error actualizando tramo dado un producto");
	    Mockito.when(propertiesMsgMock.getLogger_error_executing_delete_tramo_by_product()).thenReturn("Error eliminando tramo dado un producto");
	    
	}

	@Test
	public void testWhenOkGetTerminosCortosByProduct() {
		 Mockito.when(productoService.getTerminosCortosByProduct(1L)).thenReturn(getListaTerminosCortosDtoMock());
		 ResponseEntity<List<TerminoCortoDto>> response = productoController.getTerminosCortosByProduct(1L);
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = ResourceNotFoundException.class )
	public void testWhenError404GetTerminosCortosByProduct() {
		 Mockito.when(productoService.getTerminosCortosByProduct(1L)).thenThrow(ResourceNotFoundException.class);
		 productoController.getTerminosCortosByProduct(1L);		 
	}
	
	@Test(expected = ForbiddenException.class )
	public void testWhenError403GetTerminosCortosByProduct() {
		 Mockito.when(productoService.getTerminosCortosByProduct(1L)).thenThrow(ForbiddenException.class);
		 productoController.getTerminosCortosByProduct(1L);		 
	}
	
	@Test(expected = ProductoException.class )
	public void testWhenError500GetTerminosCortosByProduct() {
		 Mockito.when(productoService.getTerminosCortosByProduct(1L)).thenThrow(ProductoException.class);
		 productoController.getTerminosCortosByProduct(1L);		 
	}

	@Test
	public void testSaveWhenOkTerminosCortosByProduct() {
		Mockito.doNothing().when(productoService).saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());		
		ResponseEntity<String> response = productoController.saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());
		assertEquals(HttpStatus.OK, response.getStatusCode());		
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testSaveWhenError404TerminosCortosByProduct() {
		 Mockito.doThrow(ResourceNotFoundException.class).when(productoService).saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());		
		 productoController.saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());		
	}
	
	@Test(expected = ForbiddenException.class)
	public void testSaveWhenError403TerminosCortosByProduct() {
		 Mockito.doThrow(ForbiddenException.class).when(productoService).saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());		
		 productoController.saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());			
	}
	
	@Test(expected = ProductoException.class)
	public void testSaveWhenError500TerminosCortosByProduct() {
		 Mockito.doThrow(ProductoException.class).when(productoService).saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());		
		 productoController.saveTerminosCortosByProduct(1L, getListaTerminosCortosSaveDtoMock());			
	}

	@Test
	public void testUpdateWhenOkTerminosCortosByProduct() {
		Mockito.doNothing().when(productoService).updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));		
		ResponseEntity<String> response = productoController.updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateWhenError404TerminosCortosByProduct() {
		 Mockito.doThrow(ResourceNotFoundException.class).when(productoService).updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));		
		 productoController.updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));	
	}
	
	@Test(expected = ForbiddenException.class)
	public void testUpdateWhenError403TerminosCortosByProduct() {
		 Mockito.doThrow(ForbiddenException.class).when(productoService).updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));		
		 productoController.updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));	
	}
	
	@Test(expected = ProductoException.class)
	public void testUpdateWhenError500TerminosCortosByProduct() {
		 Mockito.doThrow(ProductoException.class).when(productoService).updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));		
		 productoController.updateTerminosCortosByProduct(1L,1L, getListaTerminosCortosSaveDtoMock().get(0));	
	}

	@Test
	public void testDeleteWhenOkTerminosCortosByProduct() {
		Mockito.doNothing().when(productoService).deleteTerminosCortosByProduct(1L,1L);		
		ResponseEntity<String> response = productoController.deleteTerminosCortosByProduct(1L,1L);	
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteWhenError404TerminosCortosByProduct() {
		 Mockito.doThrow(ResourceNotFoundException.class).when(productoService).deleteTerminosCortosByProduct(1L,1L);			
		 productoController.deleteTerminosCortosByProduct(1L,1L);	
	}
	
	@Test(expected = ForbiddenException.class)
	public void testDeleteWhenError403TerminosCortosByProduct() {
		 Mockito.doThrow(ForbiddenException.class).when(productoService).deleteTerminosCortosByProduct(1L,1L);			
		 productoController.deleteTerminosCortosByProduct(1L,1L);	
	}
	
	@Test(expected = ProductoException.class)
	public void testDeleteWhenError500TerminosCortosByProduct() {
		 Mockito.doThrow(ProductoException.class).when(productoService).deleteTerminosCortosByProduct(1L,1L);			
		 productoController.deleteTerminosCortosByProduct(1L,1L);	
	}

	@Test
	public void testWhenOkGetTramosByProduct() {
		 Mockito.when(productoService.getTramosByProduct(1L)).thenReturn(getListaTramosDtoMock());
		 ResponseEntity<List<TramoListDto>> response = productoController.getTramosByProduct(1L);
		 assertEquals(2, response.getBody().size());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testWhenError404GetTramosByProduct() {
		 Mockito.when(productoService.getTramosByProduct(1L)).thenThrow(ResourceNotFoundException.class);
		 productoController.getTramosByProduct(1L);
	}
	
	@Test(expected = ForbiddenException.class)
	public void testWhenError403GetTramosByProduct() {
		 Mockito.when(productoService.getTramosByProduct(1L)).thenThrow(ForbiddenException.class);
		 productoController.getTramosByProduct(1L);
	}
	
	@Test(expected = ProductoException.class)
	public void testWhenError500GetTramosByProduct() {
		 Mockito.when(productoService.getTramosByProduct(1L)).thenThrow(ProductoException.class);
		 productoController.getTramosByProduct(1L);
	}

	@Test
	public void testSaveWhenOkTramosByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doNothing().when(productoService).saveTramosByProduct(1L, tramoDto, 1L);		
		ResponseEntity<String> response = productoController.saveTramosByProduct(1L, tramoDto, 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testSaveWhenError404TramosByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doThrow(ResourceNotFoundException.class).when(productoService).saveTramosByProduct(1L, tramoDto, 1L);		
		productoController.saveTramosByProduct(1L, tramoDto, 1L);
	}
	
	@Test(expected = ForbiddenException.class)
	public void testSaveWhenError403TramosByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doThrow(ForbiddenException.class).when(productoService).saveTramosByProduct(1L, tramoDto, 1L);		
		productoController.saveTramosByProduct(1L, tramoDto, 1L);
	}
	
	@Test(expected = ProductoException.class)
	public void testSaveWhenError500TramosByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doThrow(ProductoException.class).when(productoService).saveTramosByProduct(1L, tramoDto, 1L);		
		productoController.saveTramosByProduct(1L, tramoDto, 1L);
	}

	@Test
	public void testUpdateWhenOkTramoByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doNothing().when(productoService).updateTramoByProduct(1L,1L, tramoDto, 1L);		
		ResponseEntity<String> response = productoController.updateTramoByProduct(1L,1L, tramoDto, 1L);	
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateWhenError404TramoByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doThrow(ResourceNotFoundException.class).when(productoService).updateTramoByProduct(1L,1L, tramoDto, 1L);		
		productoController.updateTramoByProduct(1L,1L, tramoDto, 1L);
	}
	
	@Test(expected = ProductoException.class)
	public void testUpdateWhenError500TramoByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doThrow(ProductoException.class).when(productoService).updateTramoByProduct(1L,1L, tramoDto, 1L);		
		productoController.updateTramoByProduct(1L,1L, tramoDto, 1L);
	}
	
	@Test(expected = ForbiddenException.class)
	public void testUpdateWhenError403TramoByProduct() {
		TramoDto tramoDto= createTramoDto( 1L, 2L,1L,1L, "PESA",new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));		
		Mockito.doThrow(ForbiddenException.class).when(productoService).updateTramoByProduct(1L,1L, tramoDto, 1L);		
		productoController.updateTramoByProduct(1L,1L, tramoDto, 1L);
	}

	@Test
	public void testDeleteWhenOkTramoByProductLongLong() {		
		Mockito.doNothing().when(productoService).deleteTramoByProduct(1L,1L);		
		ResponseEntity<String> response = productoController.deleteTramoByProduct(1L,1L);	
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteWhenError404TramoByProductLongLong() {		
		Mockito.doThrow(ResourceNotFoundException.class).when(productoService).deleteTramoByProduct(1L,1L);		
		productoController.deleteTramoByProduct(1L,1L);	
	}
	
	@Test(expected = ForbiddenException.class)
	public void testDeleteWhenError403TramoByProductLongLong() {		
		Mockito.doThrow(ForbiddenException.class).when(productoService).deleteTramoByProduct(1L,1L);		
		productoController.deleteTramoByProduct(1L,1L);	
	}
	
	@Test(expected = ProductoException.class)
	public void testDeleteWhenError500TramoByProductLongLong() {		
		Mockito.doThrow(ProductoException.class).when(productoService).deleteTramoByProduct(1L,1L);		
		productoController.deleteTramoByProduct(1L,1L);	
	}

	@Test
	public void testWhenEmptyGetProductosPaginated() {
		PageProductoDto pageProductDtoNew= new PageProductoDto();
		pageProductDtoNew.setProductos(new ArrayList<ProductoPageDto>());
		
		Mockito.when(productoService.findAllPaginated(1, 10, 1, 2, 1, null, null)).thenReturn(pageProductDtoNew);
		
		ResponseEntity<PageProductoDto> pageProductDto= productoController.getProductosPaginated(1, 10, 1, 2, 1, null, null);
		
		assertTrue(pageProductDto.getBody().getProductos().isEmpty());
	}

	@Test(expected = ProductoException.class)
	public void testWhenError500ProductosPaginated() {		
		Mockito.when(productoService.findAllPaginated(1, 10, 1, 2, 1, null, null)).thenThrow(ProductoException.class);		
		productoController.getProductosPaginated(1, 10, 1, 2, 1, null, null);		
	}

	
	@Test
	public void testWhenOkgetInfoProducto() {	
		InfoProductoDto infoProductoDto=new InfoProductoDto();
		
		infoProductoDto.setCompania("SEG. GENERALES SURAMERICANA");
		infoProductoDto.setIdTipoCompania(1L);;
		infoProductoDto.setNegocio("LINEAS PERSONALES");
		infoProductoDto.setNemotecnico("AAAA");
		infoProductoDto.setRamo("ASISTENCIA DE VEHICULOS");
		infoProductoDto.setPlan("SERVICIOS VEGASISTENCIA");
		infoProductoDto.setId(1L);
		
		TipoRamoDto tipoRamo=new TipoRamoDto();
		tipoRamo.setId(1L);
		tipoRamo.setNombre("Vehículos");
		
		infoProductoDto.setTipoRamo(tipoRamo);
		
		Mockito.when(productoService.getInfoProducto(1L)).thenReturn(infoProductoDto);				
		
		ResponseEntity<InfoProductoDto> infoProductoDtoRequest = productoController.getInfoProducto(1L);	
		
		assertEquals(infoProductoDto, infoProductoDtoRequest.getBody());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testWhenError404getInfoProducto() {	
		
		 Mockito.when(productoService.getInfoProducto(1L)).thenThrow(ResourceNotFoundException.class);				
		
		 productoController.getInfoProducto(1L);	
	}
	
	@Test(expected = ProductoException.class)
	public void testWhenError500getInfoProducto() {	
		
		 Mockito.when(productoService.getInfoProducto(1L)).thenThrow(ProductoException.class);				
		
		 productoController.getInfoProducto(1L);	
	}
	
	
	private List<TerminoCortoDto> getListaTerminosCortosDtoMock() {
		List<TerminoCortoDto> lista= new ArrayList<>();
		
		TerminoCortoDto terminoCortoDto1=createTerminoCortoDto(1L, new TipoMultaDto(1L,"Monto",""), 1, 3, new BigDecimal(10.10), new BigDecimal(50.45), 0, "PESO");
		TerminoCortoDto terminoCortoDto2=createTerminoCortoDto(1L, new TipoMultaDto(1L,"Monto",""), 3, 6, new BigDecimal(50.10), new BigDecimal(60.45), 0, "PESO");	
		
		lista.add(terminoCortoDto1);
		lista.add(terminoCortoDto2);
		
		return lista;		
	}
	
	private List<TerminoCortoSaveDto> getListaTerminosCortosSaveDtoMock() {
		List<TerminoCortoSaveDto> lista= new ArrayList<>();
		
		TerminoCortoSaveDto terminoCortoDto1=createTerminoCortoSaveDto(1L, 1L, 1, 3, new BigDecimal(10.10), new BigDecimal(50.45), 0, "PESO");
		TerminoCortoSaveDto terminoCortoDto2=createTerminoCortoSaveDto(1L,1L, 3, 6, new BigDecimal(50.10), new BigDecimal(60.45), 0, "PESO");	
		
		lista.add(terminoCortoDto1);
		lista.add(terminoCortoDto2);
		
		return lista;		
	}
	
	private List<TramoListDto> getListaTramosDtoMock() {
		List<TramoListDto> lista= new ArrayList<>();
		
		TramoListDto tramoDto1= createTramoListDto(1L, new TipoTasaDto(1L,"%",""), new TipoTramoDto(2L,"Monto",""), new TarifaEsDto(1L,"Valor",""), new PrimaSobreQueDto(1L,"Edificio",""), "PESA", new BigDecimal(10.10), new BigDecimal(20), new BigDecimal(50));
		TramoListDto tramoDto2= createTramoListDto(1L, new TipoTasaDto(1L,"%",""), new TipoTramoDto(2L,"Monto",""), new TarifaEsDto(1L,"Valor",""), new PrimaSobreQueDto(1L,"Edificio",""), "PESA", new BigDecimal(20), new BigDecimal(25), new BigDecimal(150));
		
		lista.add(tramoDto1);
		lista.add(tramoDto2);
		
		return lista;		
	}
	
	
	private TerminoCortoDto createTerminoCortoDto(Long id, TipoMultaDto tipoMulta, Integer mesDesde, Integer mesHasta, BigDecimal primaPeriodo, BigDecimal monto, Integer cuotas, String moneda) {
		TerminoCortoDto terminoCortoDto= new TerminoCortoDto();
		terminoCortoDto.setId(id);
		terminoCortoDto.setCuotas(cuotas);
		terminoCortoDto.setMesDesde(mesDesde);
		terminoCortoDto.setMesHasta(mesHasta);
		terminoCortoDto.setMoneda(moneda);
		terminoCortoDto.setMonto(monto);
		terminoCortoDto.setPrimaPeriodo(primaPeriodo);
		terminoCortoDto.setTipoMulta(tipoMulta);
		
		return terminoCortoDto;		
		
	}
	
	private TerminoCortoSaveDto createTerminoCortoSaveDto(Long id, Long tipoMulta, Integer mesDesde, Integer mesHasta, BigDecimal primaPeriodo, BigDecimal monto, Integer cuotas, String moneda) {
		TerminoCortoSaveDto terminoCortoDto= new TerminoCortoSaveDto();
		terminoCortoDto.setId(id);
		terminoCortoDto.setCuotas(cuotas);
		terminoCortoDto.setMesDesde(mesDesde);
		terminoCortoDto.setMesHasta(mesHasta);
		terminoCortoDto.setMoneda(moneda);
		terminoCortoDto.setMonto(monto);
		terminoCortoDto.setPrimaPeriodo(primaPeriodo);
		terminoCortoDto.setTipoMulta(tipoMulta);
		
		return terminoCortoDto;		
		
	}
	
	private TramoListDto createTramoListDto(Long id, TipoTasaDto tipoTasa, TipoTramoDto tipoTramo, TarifaEsDto tarifaEs, PrimaSobreQueDto tramoPara, String moneda, BigDecimal valorDesde, BigDecimal valorHasta, BigDecimal valor) {
		TramoListDto tramoListDto= new TramoListDto();
		tramoListDto.setId(id);
		tramoListDto.setTarifaEs(tarifaEs);
		tramoListDto.setMoneda(moneda);
		tramoListDto.setTipoTasa(tipoTasa);
		tramoListDto.setTipoTramo(tipoTramo);
		tramoListDto.setValor(valor);
		tramoListDto.setValorDesde(valorDesde);
		tramoListDto.setValorHasta(valorHasta);
		tramoListDto.setTramoPara(tramoPara);		
		
		return tramoListDto;		
		
	}
	
	private TramoDto createTramoDto( Long tipoTasa, Long tipoTramo, Long tarifaEs, Long tramoPara, String moneda, BigDecimal valorDesde, BigDecimal valorHasta, BigDecimal valor) {
		TramoDto tramoDto= new TramoDto();
		tramoDto.setId(null);
		tramoDto.setTarifaEs(tarifaEs);
		tramoDto.setMoneda(moneda);
		tramoDto.setTipoTasa(tipoTasa);
		tramoDto.setTipoTramo(tipoTramo);
		tramoDto.setValor(valor);
		tramoDto.setValorDesde(valorDesde);
		tramoDto.setValorHasta(valorHasta);
		tramoDto.setTramoPara(tramoPara);		
		
		return tramoDto;		
		
	}
	
}
