package seguros.producto.gestionarproducto.controllers;



import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import seguros.producto.gestionarproducto.dto.EstadoProductoDto;
import seguros.producto.gestionarproducto.dto.InfoProductoDto;
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoDto;
import seguros.producto.gestionarproducto.dto.TerminoCortoSaveDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.services.ProductoService;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

@RestController
@Api(value="Producto Resource")
@RefreshScope
@RequestMapping("/producto")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ") 
public class ProductoController {
	
	
	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_SAVE_PRODUCT = "Registrar producto";
	private static final String SWAGGER_GET_PRODUCT_PAGINATED = "Listar productos paginado";
	private static final String SWAGGER_HABILITAR_DESHABILITAR_PRODUCT = "Habilitar/deshabilitar producto";
	private static final String SWAGGER_GET_TERMINOS_CORTOS_BY_PRODUCT = "Obtener t\u00E9rminos cortos de un producto dado";
	private static final String SWAGGER_SAVE_TERMINOS_CORTOS_BY_PRODUCT = "Registrar t\u00E9rminos cortos dado un producto";
	private static final String SWAGGER_DELETE_TERMINOS_CORTOS_BY_PRODUCT = "Eliminar t\u00E9rminos cortos dado un producto";
	private static final String SWAGGER_UPDATE_TERMINOS_CORTOS_BY_PRODUCT = "Actualizar t\u00E9rminos cortos dado un producto";
	private static final String SWAGGER_GET_INFO_PRODUCTO = "Obtener informacion resumida de producto";

	
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
	public ResponseEntity<List<TerminoCortoDto>> getTerminosCortosByProduct(@PathVariable("id") Long id) throws ProductoException,ResourceNotFoundException{	
		List<TerminoCortoDto> lista=null;
		try {	
			lista= productoService.getTerminosCortosByProduct(id);
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
			) throws ProductoException,ResourceNotFoundException {	
		
		try {	
			productoService.saveTerminosCortosByProduct(id,terminosCortos);
		}
		catch(ResourceNotFoundException e) {
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
			) throws ProductoException,ResourceNotFoundException {	
		
		try {	
			productoService.updateTerminosCortosByProduct(idProducto, idTerminoCorto, terminosCorto);
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
			) throws ProductoException,ResourceNotFoundException {	
		
		try {	
			productoService.deleteTerminosCortosByProduct(idProducto,idTerminoCorto);
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

}
