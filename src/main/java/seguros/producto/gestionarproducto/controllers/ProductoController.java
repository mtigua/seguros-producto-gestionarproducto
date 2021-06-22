package seguros.producto.gestionarproducto.controllers;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.dto.CoberturaProductoDto;
import seguros.producto.gestionarproducto.dto.EstadoProductoDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.dto.TramoDto;
import seguros.producto.gestionarproducto.dto.TramoListDto;
import seguros.producto.gestionarproducto.dto.RecargoPorAseguradoDto;
import seguros.producto.gestionarproducto.dto.CoberturaDTO;
import seguros.producto.gestionarproducto.dto.OrdenCoberturaDTO;
import seguros.producto.gestionarproducto.dto.CoberturaProductoCorrelativoDto;
import seguros.producto.gestionarproducto.dto.TipoIvaDTO;
import seguros.producto.gestionarproducto.dto.DeducibleDTO;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.services.ProductoService;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Api(value="Producto Resource")
@RefreshScope
@RequestMapping("/producto")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ")
public class ProductoController {


	private static final String SWAGGER_GET_COBERTURAS_BY_PRODUCT_CORRELATIVE = "Obtener coberturas de un producto dado y correlativo";
	private static final String SWAGGER_GET_COBERTURAS_TIPO_IVA = "Obtener tipo iva";
	private static final String SWAGGER_GET_DEDUCIBLES = "Obtener deducibles";
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_SAVE_PRODUCT = "Registrar producto";
	private static final String SWAGGER_SAVE_COBERTURA = "Registrar cobertura del producto";
	private static final String SWAGGER_SAVE_ORDEN_COBERTURA = "Actualiza orden la cobertura del producto";
	private static final String SWAGGER_GET_PRODUCT_PAGINATED = "Listar productos paginado";
	private static final String SWAGGER_HABILITAR_DESHABILITAR_PRODUCT = "Habilitar/deshabilitar producto";
	private static final String SWAGGER_GET_TERMINOS_CORTOS_BY_PRODUCT = "Obtener t\u00E9rminos cortos de un producto dado";
	private static final String SWAGGER_SAVE_TERMINOS_CORTOS_BY_PRODUCT = "Registrar t\u00E9rminos cortos dado un producto";
	private static final String SWAGGER_DELETE_TERMINOS_CORTOS_BY_PRODUCT = "Eliminar t\u00E9rminos cortos dado un producto";
	private static final String SWAGGER_UPDATE_TERMINOS_CORTOS_BY_PRODUCT = "Actualizar t\u00E9rminos cortos dado un producto";
	private static final String SWAGGER_GET_INFO_PRODUCTO = "Obtener informacion resumida de producto";
	private static final String SWAGGER_GET_COBERTURAS_BY_PRODUCT = "Obtener coberturas de un producto dado";
	private static final String SWAGGER_GET_TRAMOS_BY_PRODUCT = "Obtener tramoS de un producto dado";
	private static final String SWAGGER_SAVE_TRAMO_BY_PRODUCT = "Registrar tramo dado un producto";
	private static final String SWAGGER_DELETE_TRAMO_BY_PRODUCT = "Eliminar tramo dado un producto";
	private static final String SWAGGER_UPDATE_TRAMO_BY_PRODUCT = "Actualizar tramodado un producto";
	private static final String SWAGGER_GET_RECARGO_POR_SEGURADO_BY_PRODUCT = "Obtener recargo por asegurado de un producto dado";
	private static final String SWAGGER_SAVE_RECARGO_POR_SEGURADO_BY_PRODUCT = "Registrar recargo por asegurado dado un producto";
	private static final String SWAGGER_DELETE_RECARGO_POR_SEGURADO_BY_PRODUCT = "Eliminar recargo por asegurado dado un producto";
	private static final String SWAGGER_UPDATE_RECARGO_POR_SEGURADO_BY_PRODUCT = "Actualizar recargo por asegurado dado un producto";

	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private ProductoService productoService;


	@ApiOperation(value = SWAGGER_SAVE_PRODUCT, notes = SWAGGER_SAVE_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/")
	public ResponseEntity<InfoProductoDto> saveProducto(

			@RequestBody(required = false) @Valid ProductoDto producto
			) throws ProductoException{	
				
		InfoProductoDto result=null;
		
		try {	
			result= productoService.save(producto);
			   
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_producto());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_producto());
			throw ex;
		}		

		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = SWAGGER_SAVE_ORDEN_COBERTURA, notes = SWAGGER_SAVE_ORDEN_COBERTURA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/changeOrderCobertura")
	public void changeOrderCobertura(

			@RequestBody(required = false) @Valid OrdenCoberturaDTO ordenCoberturaDTO
	) throws ProductoException{

		try {
			productoService.updateOrderCobertura(ordenCoberturaDTO);
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_orden_cobertura());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_orden_cobertura());
			throw ex;
		}

	}


	@ApiOperation(value = SWAGGER_SAVE_COBERTURA, notes = SWAGGER_SAVE_COBERTURA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/cobertura")
	public void saveCobertura(
			@RequestBody(required = false) @Valid CoberturaDTO cobertura
	) throws ProductoException{

		try {
			productoService.saveCoberturaProducto(cobertura);
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_cobertura());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_cobertura());
			throw ex;
		}

	}

	
	@ApiOperation(value = SWAGGER_GET_PRODUCT_PAGINATED, notes = SWAGGER_GET_PRODUCT_PAGINATED)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/findAllPaginated")
	public ResponseEntity<PageProductoDto> getProductosPaginated(
			    @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(required = false) Integer idCompania,
	            @RequestParam(required = false) Integer idNegocio,
	            @RequestParam(required = false) Integer idRamo,
	            @RequestParam(required = false) String nemotecnico,
	            @RequestParam(required = false) String descripcion
	            
			) throws ProductoException{	
				
		PageProductoDto pageProductoDto= null;
		
		try {
			pageProductoDto = productoService.findAllPaginated(page,size,idCompania,idNegocio,idRamo,nemotecnico,descripcion);
			 
			   
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw ex;
		}		

		return ResponseEntity.ok(pageProductoDto);
	}	
	
	
	@ApiOperation(value = SWAGGER_HABILITAR_DESHABILITAR_PRODUCT, notes = SWAGGER_HABILITAR_DESHABILITAR_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/enableDisable")
	public ResponseEntity<String> enableDisableProducto(
			@RequestBody @Valid EstadoProductoDto estadoProductoDto
		) throws ProductoException{	
		
		try {	
			productoService.enableDisable(estadoProductoDto);
		}
		catch(ProductoException e) {
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			throw ex;
		}		

		return ResponseEntity.ok(MSG_HTTP200);
	}
	
	@ApiOperation(value = SWAGGER_GET_TERMINOS_CORTOS_BY_PRODUCT, notes = SWAGGER_GET_TERMINOS_CORTOS_BY_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/terminosCortos")
	public ResponseEntity<List<TerminoCortoDto>> getTerminosCortosByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException{
		List<TerminoCortoDto> lista=null;
		try {	
			lista= productoService.getTerminosCortosByProduct(id);
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_terminos_corto_by_product());
			throw e;
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_terminos_corto_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_terminos_corto_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_terminos_corto_by_product());
			throw ex;
		}		

		return ResponseEntity.ok(lista);
	}
	
	@ApiOperation(value = SWAGGER_SAVE_TERMINOS_CORTOS_BY_PRODUCT, notes = SWAGGER_SAVE_TERMINOS_CORTOS_BY_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/terminosCortos")
	public ResponseEntity<String> saveTerminosCortosByProduct(
			@PathVariable("id") Long id,
			@RequestBody @Valid List<TerminoCortoSaveDto> terminosCortos			
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		
		try {	
			productoService.saveTerminosCortosByProduct(id,terminosCortos);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_terminos_corto_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_terminos_corto_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_terminos_corto_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_terminos_corto_by_product());
			throw ex;
		}		

		return ResponseEntity.ok(MSG_HTTP200);
	}
	
	@ApiOperation(value = SWAGGER_UPDATE_TERMINOS_CORTOS_BY_PRODUCT, notes = SWAGGER_UPDATE_TERMINOS_CORTOS_BY_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}/terminosCortos/{idTerminoCorto}")
	public ResponseEntity<String> updateTerminosCortosByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTerminoCorto") Long idTerminoCorto,
			@RequestBody @Valid TerminoCortoSaveDto terminosCorto	
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		
		try {	
			productoService.updateTerminosCortosByProduct(idProducto, idTerminoCorto, terminosCorto);
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_terminos_corto_by_product());
			throw e;
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_terminos_corto_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_terminos_corto_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_terminos_corto_by_product());
			throw ex;
		}		

		return ResponseEntity.ok(MSG_HTTP200);
	}
	
	
	@ApiOperation(value = SWAGGER_DELETE_TERMINOS_CORTOS_BY_PRODUCT, notes = SWAGGER_DELETE_TERMINOS_CORTOS_BY_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}/terminosCortos/{idTerminoCorto}")
	public ResponseEntity<String> deleteTerminosCortosByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTerminoCorto") Long idTerminoCorto		
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {
		
		try {	
			productoService.deleteTerminosCortosByProduct(idProducto,idTerminoCorto);
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_terminos_corto_by_product());
			throw e;
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_terminos_corto_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_terminos_corto_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_delete_terminos_corto_by_product());
			throw ex;
		}		

		return ResponseEntity.ok(MSG_HTTP200);
	}
	
	@ApiOperation(value = SWAGGER_GET_INFO_PRODUCTO, notes = SWAGGER_GET_INFO_PRODUCTO)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/info")
	public ResponseEntity<InfoProductoDto> getInfoProducto(
			@PathVariable("id") Long idProducto	
			) throws ProductoException,ResourceNotFoundException {	
		InfoProductoDto infoProductoDto=null;
		try {	
			infoProductoDto= productoService.getInfoProducto(idProducto);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_info_producto());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_info_producto());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_info_producto());
			throw ex;
		}		

		return ResponseEntity.ok(infoProductoDto);
	}

	@ApiOperation(value = SWAGGER_GET_COBERTURAS_BY_PRODUCT, notes = SWAGGER_GET_COBERTURAS_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/coberturas")
	public ResponseEntity<List<CoberturaProductoDto>> getCoberturasByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException{
		List<CoberturaProductoDto> coberturasProductoDto=null;
		try {
			coberturasProductoDto= productoService.getCoberturasDtoByProducto(id);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product());
			throw ex;
		}
		return ResponseEntity.ok(coberturasProductoDto);
	}


	@ApiOperation(value = SWAGGER_GET_TRAMOS_BY_PRODUCT, notes = SWAGGER_GET_TRAMOS_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/tramo")
	public ResponseEntity<List<TramoListDto>> getTramosByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException{
		List<TramoListDto> lista=null;
		try {
			lista= productoService.getTramosByProduct(id);
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw e;
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw ex;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_SAVE_TRAMO_BY_PRODUCT, notes = SWAGGER_SAVE_TRAMO_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/tramo")
	public ResponseEntity<String> saveTramosByProduct(
			@PathVariable("id") Long id,
			@RequestBody @Valid TramoDto tramoDto,
			@RequestParam(required = false) Long tipoRamo
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.saveTramosByProduct(id,tramoDto,tipoRamo);
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw e;
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_UPDATE_TRAMO_BY_PRODUCT, notes = SWAGGER_UPDATE_TRAMO_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}/tramo/{idTramo}")
	public ResponseEntity<String> updateTramoByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTramo") Long idTramo,
			@RequestBody @Valid TramoDto tramoDto,
			@RequestParam(required = false) Long tipoRamo
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.updateTramoByProduct(idProducto, idTramo, tramoDto,tipoRamo);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}


	@ApiOperation(value = SWAGGER_DELETE_TRAMO_BY_PRODUCT, notes = SWAGGER_DELETE_TRAMO_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}/tramo/{idTramo}")
	public ResponseEntity<String> deleteTramoByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTramo") Long idTramo
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.deleteTramoByProduct(idProducto,idTramo);
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e;
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_GET_COBERTURAS_BY_PRODUCT_CORRELATIVE, notes = SWAGGER_GET_COBERTURAS_BY_PRODUCT_CORRELATIVE)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/coberturaProductoCorrelativo")
	public ResponseEntity<List<CoberturaProductoCorrelativoDto>> getCoberturaByProductoCorrelativo(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException{
		List<CoberturaProductoCorrelativoDto> coberturaProductoCorrelativoDtos =null;
		try {
			coberturaProductoCorrelativoDtos = productoService.getCoberturasDtoByProductoCorrelative(id);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product_correlative());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product_correlative());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product_correlative());
			throw ex;
		}
		return ResponseEntity.ok(coberturaProductoCorrelativoDtos);
	}

	@ApiOperation(value = SWAGGER_GET_COBERTURAS_TIPO_IVA, notes = SWAGGER_GET_COBERTURAS_TIPO_IVA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/tipoIvaPorProducto")
	public ResponseEntity<List<TipoIvaDTO>> getTipoIva(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException{
		List<TipoIvaDTO> tipoIvas = null;
		try {
			tipoIvas = productoService.getTipoIvaByProducto(id);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_tipo_iva_by_producto());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_tipo_iva_by_producto());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_tipo_iva_by_producto());
			throw ex;
		}
		return ResponseEntity.ok(tipoIvas);
	}

	@ApiOperation(value = SWAGGER_GET_DEDUCIBLES, notes = SWAGGER_GET_DEDUCIBLES)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/listarDeducible/{idCobertura}/cobertura")
	public ResponseEntity<List<DeducibleDTO>> getDeducibles(
			@PathVariable("id") Long id,
			@PathVariable("idCobertura") Long idCobertura
	)
			throws ProductoException,ResourceNotFoundException{
		List<DeducibleDTO> deducibles = null;
		try {
			deducibles = productoService.getDeducibles(id, idCobertura);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_deducibles());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_deducibles());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_deducibles());
			throw ex;
		}
		return ResponseEntity.ok(deducibles);
	}

	@ApiOperation(value = SWAGGER_GET_RECARGO_POR_SEGURADO_BY_PRODUCT, notes = SWAGGER_GET_RECARGO_POR_SEGURADO_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/recargoPorAsegurado")
	public ResponseEntity<List<RecargoPorAseguradoDto>> getRecargoPorAseguradoByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException, ForbiddenException{
		List<RecargoPorAseguradoDto> lista=null;
		try {
			lista= productoService.getRecargoPorAseguradoByProduct(id);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_recargo_por_asegurado_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_recargo_por_asegurado_by_product());
			throw ex;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_SAVE_RECARGO_POR_SEGURADO_BY_PRODUCT, notes = SWAGGER_SAVE_RECARGO_POR_SEGURADO_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/recargoPorAsegurado")
	public ResponseEntity<String> saveRecargoPorAseguradoByProduct(
			@PathVariable("id") Long id,
			@RequestBody @Valid List<RecargoPorAseguradoDto> recargoPorAsegurado
	) throws ProductoException,ResourceNotFoundException, ForbiddenException {

		try {
			productoService.saveRecargoPorAseguradoByProduct(id,recargoPorAsegurado);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_recargo_por_asegurado_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_recargo_por_asegurado_by_product());
			throw ex;
		}
		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_UPDATE_RECARGO_POR_SEGURADO_BY_PRODUCT, notes = SWAGGER_UPDATE_RECARGO_POR_SEGURADO_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}/recargoPorAsegurado/{idRecargoPorAsegurado}")
	public ResponseEntity<String> updateRecargoPorAseguradoByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idRecargoPorAsegurado") Long idRecargoPorAsegurado,
			@RequestBody @Valid RecargoPorAseguradoDto recargoPorAsegurado
	) throws ProductoException,ResourceNotFoundException, ForbiddenException {

		try {
			productoService.updateRecargoPorAseguradoByProduct(idProducto, idRecargoPorAsegurado, recargoPorAsegurado);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_recargo_por_asegurado_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_recargo_por_asegurado_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}


	@ApiOperation(value = SWAGGER_DELETE_RECARGO_POR_SEGURADO_BY_PRODUCT, notes = SWAGGER_DELETE_RECARGO_POR_SEGURADO_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}/recargoPorAsegurado/{idRecargoPorAsegurado}")
	public ResponseEntity<String> deleteRecargoPorAseguradoByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idRecargoPorAsegurado") Long idRecargoPorAsegurado
	) throws ProductoException,ResourceNotFoundException, ForbiddenException {

		try {
			productoService.deleteRecargoPorAseguradoByProduct(idProducto,idRecargoPorAsegurado);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_recargo_por_asegurado_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_recargo_por_asegurado_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_delete_recargo_por_asegurado_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}
}
