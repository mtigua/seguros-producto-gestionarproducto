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
import seguros.producto.gestionarproducto.dto.ProductoDetallePromocionDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.dto.TramoDto;
import seguros.producto.gestionarproducto.dto.TramoListDto;
import seguros.producto.gestionarproducto.dto.RecargoPorAseguradoDto;
import seguros.producto.gestionarproducto.dto.PlanUpgradeDto;
import seguros.producto.gestionarproducto.dto.ProdDto;
import seguros.producto.gestionarproducto.dto.CoberturaDTO;
import seguros.producto.gestionarproducto.dto.OrdenCoberturaDTO;
import seguros.producto.gestionarproducto.dto.CoberturaProductoCorrelativoDto;
import seguros.producto.gestionarproducto.dto.TipoIvaDTO;
import seguros.producto.gestionarproducto.dto.DeducibleDTO;
import seguros.producto.gestionarproducto.dto.DetallePromocionDto;
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
	private static final String SWAGGER_GET_TRAMOS_BY_PRODUCT_COBERTURA = "Obtener tramos de un producto y cobertura";
	private static final String SWAGGER_SAVE_TRAMO_BY_PRODUCT = "Registrar tramo dado un producto";
	private static final String SWAGGER_SAVE_TRAMO_BY_PRODUCT_COBERTURA = "Registrar tramo dado un producto y cobertura";
	private static final String SWAGGER_DELETE_TRAMO_BY_PRODUCT = "Eliminar tramo dado un producto";
	private static final String SWAGGER_DELETE_TRAMO_BY_PRODUCT_COBERTURA = "Eliminar tramo dado una cobertura asociado a un producto";
	private static final String SWAGGER_UPDATE_TRAMO_BY_PRODUCT = "Actualizar tramodado un producto";
	private static final String SWAGGER_UPDATE_TRAMO_BY_PRODUCT_AND_COBERTURA = "Actualizar tramo dado un producto y cobertura";
	private static final String SWAGGER_GET_RECARGO_POR_SEGURADO_BY_PRODUCT = "Obtener recargo por asegurado de un producto dado";
	private static final String SWAGGER_SAVE_RECARGO_POR_SEGURADO_BY_PRODUCT = "Registrar recargo por asegurado dado un producto";
	private static final String SWAGGER_DELETE_RECARGO_POR_SEGURADO_BY_PRODUCT = "Eliminar recargo por asegurado dado un producto";
	private static final String SWAGGER_UPDATE_RECARGO_POR_SEGURADO_BY_PRODUCT = "Actualizar recargo por asegurado dado un producto";
	private static final String SWAGGER_GET_DETALLES_PROMOCION_BY_PRODUCT = "Obtener los detalles de promocion de un producto dado";
	private static final String SWAGGER_SAVE_DETALLE_PROMOCION_BY_PRODUCT = "Registrar el detalle de una promocion dado un producto";
	private static final String SWAGGER_UPDATE_DETALLE_PROMOCION_BY_PRODUCT = "Actualizar el detalle de una promocion dado un producto";
	private static final String SWAGGER_DELETE_DETALLE_PROMOCION_BY_PRODUCT = "Eliminar el detalle de una promocion dado un producto";
	private static final String SWAGGER_GET_PLAN_UPGRADE_BY_PRODUCT = "Obtener los planes upgrades de un producto dado";
	private static final String SWAGGER_GET_PRODUCTO_BY_NEMO = "Obtener los productos dado un nemotecnico";
	private static final String SWAGGER_GET_PLAN_EXISTENTE_BY_NEMO = "Obtener los planes existentes dado un nemotecnico";
	private static final String SWAGGER_GET_PLAN_ACEPTADO_BY_NEMO = "Obtener los planes aceptados dado un nemotecnico";
	private static final String SWAGGER_SAVE_PLAN_UPGRADE_BY_PRODUCT = "Registrar planes upgrades dado un producto";
	private static final String SWAGGER_DELETE_PLAN_UPGRADE_BY_PRODUCT = "Eliminar plan upgrade dado un producto";
	private static final String SWAGGER_UPDATE_PLAN_UPGRADE_BY_PRODUCT = "Actualizar plan upgrade dado un producto";
	
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
		catch(ResourceNotFoundException | ForbiddenException e35) {
			throw e35;
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
		catch(ForbiddenException exceptionTramo1) {
			exceptionTramo1.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw exceptionTramo1;
		}
		catch(ResourceNotFoundException notFoundExceptionTramo) {
			notFoundExceptionTramo.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw notFoundExceptionTramo;
		}
		catch(ProductoException productException1) {
			productException1.setSubject(propertiesMsg.getLogger_error_executing_get_tramo_by_product());
			throw productException1;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_GET_TRAMOS_BY_PRODUCT_COBERTURA, notes = SWAGGER_GET_TRAMOS_BY_PRODUCT_COBERTURA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/tramoCobertura/{idCobertura}/tramo")
	public ResponseEntity<List<TramoListDto>> getTramosByCobertura(
			@PathVariable("id") Long id,
			@PathVariable("idCobertura") Long idCobertura) throws ProductoException,ResourceNotFoundException,ForbiddenException{
		List<TramoListDto> lista=null;
		try {
			lista= productoService.getTramosByCobertura(id, idCobertura);
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
		catch(ForbiddenException exceptionTipoRamo) {
			exceptionTipoRamo.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw exceptionTipoRamo;
		}
		catch(ResourceNotFoundException exceptionNotFound4) {
			exceptionNotFound4.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw exceptionNotFound4;
		}
		catch(ProductoException exceptionProductoError) {
			exceptionProductoError.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw exceptionProductoError;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_SAVE_TRAMO_BY_PRODUCT_COBERTURA, notes = SWAGGER_SAVE_TRAMO_BY_PRODUCT_COBERTURA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/tramoCobertura/{idCobertura}/tramo")
	public ResponseEntity<String> saveTramoByProductCobertura(
			@PathVariable("id") Long productId,
			@PathVariable("idCobertura") Long coberturaId,
			@RequestBody @Valid TramoDto tramoDto,
			@RequestParam(required = false) Long tipoRamo
	) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.saveTramosByProductCobertura(productId, coberturaId, tramoDto,tipoRamo);
		}
		catch(ForbiddenException exceptionProductoCobertura10) {
			exceptionProductoCobertura10.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw exceptionProductoCobertura10;
		}
		catch(ResourceNotFoundException exceptionNotFoundProductoCobertura) {
			exceptionNotFoundProductoCobertura.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw exceptionNotFoundProductoCobertura;
		}
		catch(ProductoException productoExceptionError10) {
			productoExceptionError10.setSubject(propertiesMsg.getLogger_error_executing_save_tramo_by_product());
			throw productoExceptionError10;
		}
		catch (Exception exceptionProducto10) {
			ProductoException ex = new ProductoException(exceptionProducto10);
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
	@PutMapping("/{id}/tramo/{idTramo}/tramo")
	public ResponseEntity<String> updateTramoByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTramo") Long idTramo,
			@RequestBody @Valid TramoDto tramoDto,
			@RequestParam(required = false) Long tipoRamo
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.updateTramoByProduct(idProducto, idTramo, tramoDto,tipoRamo);
		}
		catch(ResourceNotFoundException notFoundExceptionUpdate) {
			notFoundExceptionUpdate.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw notFoundExceptionUpdate;
		}
		catch(ForbiddenException exception11) {
			exception11.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw exception11;
		}
		catch(ProductoException productoException11) {
			productoException11.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw productoException11;
		}
		catch (Exception exception11) {
			ProductoException ex = new ProductoException(exception11);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_UPDATE_TRAMO_BY_PRODUCT_AND_COBERTURA, notes = SWAGGER_UPDATE_TRAMO_BY_PRODUCT_AND_COBERTURA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}/tramoCobertura/{idCobertura}/tramo/{idTramo}")
	public ResponseEntity<String> updateTramoByCobertura(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTramo") Long idTramo,
			@PathVariable("idCobertura") Long idCobertura,
			@RequestBody @Valid TramoDto tramoDto,
			@RequestParam(required = false) Long tipoRamo
	) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.updateTramoByCobertura(idProducto, idCobertura, idTramo, tramoDto,tipoRamo);
		}
		catch(ResourceNotFoundException notFoundExceptionTipoRamo) {
			notFoundExceptionTipoRamo.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw notFoundExceptionTipoRamo;
		}
		catch(ForbiddenException exceptionTipoRamo12) {
			exceptionTipoRamo12.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw exceptionTipoRamo12;
		}
		catch(ProductoException productoException12) {
			productoException12.setSubject(propertiesMsg.getLogger_error_executing_update_tramo_by_product());
			throw productoException12;
		}
		catch (Exception exception12) {
			ProductoException ex = new ProductoException(exception12);
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
		catch(ForbiddenException e13) {
			e13.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e13;
		}
		catch(ResourceNotFoundException e14) {
			e14.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e14;
		}
		catch(ProductoException e15) {
			e15.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e15;
		}
		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_DELETE_TRAMO_BY_PRODUCT_COBERTURA, notes = SWAGGER_DELETE_TRAMO_BY_PRODUCT_COBERTURA)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}/tramoCobertura/{idCobertura}/tramo/{idTramo}")
	public ResponseEntity<String> deleteTramoByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idTramo") Long idTramo,
			@PathVariable("idCobertura") Long idCobertura
	) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.deleteTramoByCobertura(idProducto, idCobertura, idTramo);
		}
		catch(ForbiddenException e17) {
			e17.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e17;
		}
		catch(ResourceNotFoundException e18) {
			e18.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e18;
		}
		catch(ProductoException e19) {
			e19.setSubject(propertiesMsg.getLogger_error_executing_delete_tramo_by_product());
			throw e19;
		}
		catch (Exception e20) {
			ProductoException ex = new ProductoException(e20);
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
		catch(ResourceNotFoundException e21) {
			e21.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product_correlative());
			throw e21;
		}
		catch(ProductoException e22) {
			e22.setSubject(propertiesMsg.getLogger_error_executing_get_coberturas_by_product_correlative());
			throw e22;
		}
		catch (Exception e23) {
			ProductoException ex = new ProductoException(e23);
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

	@ApiOperation(value = SWAGGER_GET_PLAN_UPGRADE_BY_PRODUCT, notes = SWAGGER_GET_PLAN_UPGRADE_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/upgrade")
	public ResponseEntity<List<PlanUpgradeDto>> getPlanUpgradeByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException, ForbiddenException{
		List<PlanUpgradeDto> lista=null;
		try {
			lista= productoService.getPlanUpgradeByProduct(id);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_upgrade_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_upgrade_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_upgrade_by_product());
			e.setDetail(propertiesMsg.getLogger_error_executing_get_plan_upgrade_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_plan_upgrade_by_product());
			throw ex;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_GET_PRODUCTO_BY_NEMO, notes = SWAGGER_GET_PRODUCTO_BY_NEMO)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/upgrade/uFilter/{uNemo}")
	public ResponseEntity<List<ProdDto>> getProductByNemo(@PathVariable("id") Long id, @PathVariable("uNemo") String nemo) throws ProductoException,ResourceNotFoundException, ForbiddenException{
		List<ProdDto> lista=null;
		try {
			lista= productoService.getProductByNemo(id,nemo);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_product_by_nemo());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_product_by_nemo());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_product_by_nemo());
			e.setDetail(propertiesMsg.getLogger_error_executing_get_product_by_nemo());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_product_by_nemo());
			throw ex;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_GET_PLAN_EXISTENTE_BY_NEMO, notes = SWAGGER_GET_PLAN_EXISTENTE_BY_NEMO)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/upgrade/pFilter/{uNemo}/{pNemo}")
	public ResponseEntity<List<ProdDto>> getPlanExistentesByProduct(@PathVariable("id") Long id, @PathVariable("uNemo") String nemoU, @PathVariable("pNemo") String nemoP) throws ProductoException,ResourceNotFoundException, ForbiddenException{
		List<ProdDto> lista=null;
		try {
			lista= productoService.getPlanesExistentesByNemo(id,nemoU,nemoP);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_existente_by_nemo());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_existente_by_nemo());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_existente_by_nemo());
			e.setDetail(propertiesMsg.getLogger_error_executing_get_plan_existente_by_nemo());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_plan_existente_by_nemo());
			throw ex;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_GET_PLAN_ACEPTADO_BY_NEMO, notes = SWAGGER_GET_PLAN_ACEPTADO_BY_NEMO)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/upgrade/accepted/{uNemo}/{pNemo}")
	public ResponseEntity<List<ProdDto>> getPlanAcceptedByProduct(@PathVariable("id") Long id, @PathVariable("uNemo") String nemoU, @PathVariable("pNemo") String nemoP) throws ProductoException,ResourceNotFoundException, ForbiddenException{
		List<ProdDto> lista=null;
		try {
			lista= productoService.getPlanesAceptadosByNemo(id,nemoU,nemoP);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_aceptado_by_nemo());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_aceptado_by_nemo());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_plan_aceptado_by_nemo());
			e.setDetail(propertiesMsg.getLogger_error_executing_get_plan_aceptado_by_nemo());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_plan_aceptado_by_nemo());
			throw ex;
		}

		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_SAVE_PLAN_UPGRADE_BY_PRODUCT, notes = SWAGGER_SAVE_PLAN_UPGRADE_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/upgrade")
	public ResponseEntity<String> saveUpgradeByProduct(
			@PathVariable("id") Long id,
			@RequestBody @Valid List<PlanUpgradeDto> upgrades
	) throws ProductoException,ResourceNotFoundException, ForbiddenException {
		try {
			productoService.saveUpgradeByProduct(id,upgrades);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_plan_upgrade_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_plan_upgrade_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_plan_upgrade_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_plan_upgrade_by_product());
			throw ex;
		}
		return ResponseEntity.ok(MSG_HTTP200);
	}

	@ApiOperation(value = SWAGGER_UPDATE_PLAN_UPGRADE_BY_PRODUCT, notes = SWAGGER_UPDATE_PLAN_UPGRADE_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}/upgrade/{idUpgrade}")
	public ResponseEntity<String> updateUpgradeByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idUpgrade") Long idUpgrade,
			@RequestBody @Valid PlanUpgradeDto upgrade
	) throws ProductoException,ResourceNotFoundException, ForbiddenException {

		try {
			productoService.updateUpgradeByProduct(idProducto, idUpgrade, upgrade);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_plan_upgrade_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_plan_upgrade_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_update_plan_upgrade_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_plan_upgrade_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}


	@ApiOperation(value = SWAGGER_DELETE_PLAN_UPGRADE_BY_PRODUCT, notes = SWAGGER_DELETE_PLAN_UPGRADE_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}/upgrade/{idUpgrade}")
	public ResponseEntity<String> deleteUpgradeByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idUpgrade") Long idUpgrade
	) throws ProductoException,ResourceNotFoundException, ForbiddenException {

		try {
			productoService.deleteUpgradeByProduct(idProducto,idUpgrade);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_plan_upgrade_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_plan_upgrade_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_delete_plan_upgrade_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_delete_plan_upgrade_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}


	@ApiOperation(value = SWAGGER_GET_DETALLES_PROMOCION_BY_PRODUCT, notes = SWAGGER_GET_DETALLES_PROMOCION_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}/detallepromocion")
	public ResponseEntity<ProductoDetallePromocionDto> getDetallePromocionByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException,ForbiddenException{
		ProductoDetallePromocionDto productoDetallePromocionDto=null;
		try {
			productoDetallePromocionDto = productoService.getDetallePromocionByProduct(id);
		}
		catch(ForbiddenException forbiddenDetallePromoGet) {
			forbiddenDetallePromoGet.setSubject(propertiesMsg.getLogger_error_executing_get_detallepromocion_by_product());
			throw forbiddenDetallePromoGet;
		}
		catch(ResourceNotFoundException notFoundDetallePromoGet) {
			notFoundDetallePromoGet.setSubject(propertiesMsg.getLogger_error_executing_get_detallepromocion_by_product());
			throw notFoundDetallePromoGet;
		}
		catch(ProductoException productDetallePromoGet) {
			productDetallePromoGet.setSubject(propertiesMsg.getLogger_error_executing_get_detallepromocion_by_product());
			throw productDetallePromoGet;
		}
		catch (Exception exDetallePromoGet) {
			ProductoException ex = new ProductoException(exDetallePromoGet);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_detallepromocion_by_product());
			throw ex;
		}
		return ResponseEntity.ok(productoDetallePromocionDto);
	}

	@ApiOperation(value = SWAGGER_SAVE_DETALLE_PROMOCION_BY_PRODUCT, notes = SWAGGER_SAVE_DETALLE_PROMOCION_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/detallepromocion")
	public ResponseEntity<String> saveDetallePromocionByProduct(
			@PathVariable("id") Long id,
			@RequestBody @Valid DetallePromocionDto detallePromocionDto
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.saveDetallePromocionByProduct(id,detallePromocionDto);
		}
		catch(ForbiddenException forbiddenDetallePromoSave) {
			forbiddenDetallePromoSave.setSubject(propertiesMsg.getLogger_error_executing_save_detallepromocion_by_product());
			throw forbiddenDetallePromoSave;
		}
		catch(ResourceNotFoundException notFoundDetallePromoSave) {
			notFoundDetallePromoSave.setSubject(propertiesMsg.getLogger_error_executing_save_detallepromocion_by_product());
			throw notFoundDetallePromoSave;
		}
		catch(ProductoException productDetallePromoSave) {
			productDetallePromoSave.setSubject(propertiesMsg.getLogger_error_executing_save_detallepromocion_by_product());
			throw productDetallePromoSave;
		}
		catch (Exception exDetallePromoSave) {
			ProductoException ex = new ProductoException(exDetallePromoSave);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_detallepromocion_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}


	@ApiOperation(value = SWAGGER_UPDATE_DETALLE_PROMOCION_BY_PRODUCT, notes = SWAGGER_UPDATE_DETALLE_PROMOCION_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}/detallepromocion/{idDetallePromocion}")
	public ResponseEntity<String> updateDetallePromocionByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idDetallePromocion") Long idDetallePromocion,
			@RequestBody @Valid DetallePromocionDto detallePromocionDto
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.updateDetallePromocionByProduct(idProducto, idDetallePromocion, detallePromocionDto);
		}
		catch(ResourceNotFoundException notFoundDetallePromoUpdate) {
			notFoundDetallePromoUpdate.setSubject(propertiesMsg.getLogger_error_executing_update_detallepromocion_by_product());
			throw notFoundDetallePromoUpdate;
		}
		catch(ForbiddenException forbiddenDetallePromoUpdate) {
			forbiddenDetallePromoUpdate.setSubject(propertiesMsg.getLogger_error_executing_update_detallepromocion_by_product());
			throw forbiddenDetallePromoUpdate;
		}
		catch(ProductoException productDetallePromoUpdate) {
			productDetallePromoUpdate.setSubject(propertiesMsg.getLogger_error_executing_update_detallepromocion_by_product());
			throw productDetallePromoUpdate;
		}
		catch (Exception exDetallePromoUpdate) {
			ProductoException ex = new ProductoException(exDetallePromoUpdate);
			ex.setSubject(propertiesMsg.getLogger_error_executing_update_detallepromocion_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}




	@ApiOperation(value = SWAGGER_DELETE_DETALLE_PROMOCION_BY_PRODUCT, notes = SWAGGER_DELETE_DETALLE_PROMOCION_BY_PRODUCT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}/detallepromocion/{idDetallePromocion}")
	public ResponseEntity<String> deleteDetallePromocionByProduct(
			@PathVariable("id") Long idProducto,
			@PathVariable("idDetallePromocion") Long idDetallePromocion
			) throws ProductoException,ResourceNotFoundException,ForbiddenException {

		try {
			productoService.deleteDetallePromocionByProduct(idProducto,idDetallePromocion);
		}
		catch(ForbiddenException forbiddenDetallePromoDel) {
			forbiddenDetallePromoDel.setSubject(propertiesMsg.getLogger_error_executing_delete_detallepromocion_by_product());
			throw forbiddenDetallePromoDel;
		}
		catch(ResourceNotFoundException notFoundDetallePromoDel) {
			notFoundDetallePromoDel.setSubject(propertiesMsg.getLogger_error_executing_delete_detallepromocion_by_product());
			throw notFoundDetallePromoDel;
		}
		catch(ProductoException productDetallePromoDel) {
			productDetallePromoDel.setSubject(propertiesMsg.getLogger_error_executing_delete_detallepromocion_by_product());
			throw productDetallePromoDel;
		}
		catch (Exception exDetallePromoDel) {
			ProductoException ex = new ProductoException(exDetallePromoDel);
			ex.setSubject(propertiesMsg.getLogger_error_executing_delete_detallepromocion_by_product());
			throw ex;
		}

		return ResponseEntity.ok(MSG_HTTP200);
	}

}
