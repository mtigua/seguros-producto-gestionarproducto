package seguros.producto.gestionarproducto.controllers;



import static seguros.producto.gestionarproducto.utils.Constants.HEADER_AUTHORIZACION_KEY;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import seguros.producto.gestionarproducto.dto.PageProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoDto;
import seguros.producto.gestionarproducto.dto.ProductoPageDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;
import seguros.producto.gestionarproducto.services.ProductoService;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;
import seguros.producto.gestionarproducto.utils.Utils;

@RestController
@Api(value="Producto Resource")
@RefreshScope
@RequestMapping("/producto")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
public class ProductoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class.getSimpleName());

	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_PRODUCT = "Listar productos";
	private static final String SWAGGER_SAVE_PRODUCT = "Registrar producto";
	private static final String SWAGGER_GET_PRODUCT_PAGINATED = "Listar productos paginado";
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private Utils utils;
	
	
	@ApiOperation(value = SWAGGER_GET_PRODUCT, notes = SWAGGER_GET_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/")
	public ResponseEntity<List<ProductoDto>> getProductos(
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			) throws ProductoException, UnauthorizedException{	
				
		List<ProductoDto> list=null;
		
		try {
			 // String username=utils.getSamaccountname(token);		
			 list= productoService.findAll();
			   
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw e;
		}
//		catch(UnauthorizedException e) {
//			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
//			throw e;
//		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw ex;
		}		

		return ResponseEntity.ok(list);
	}	
	
	@ApiOperation(value = SWAGGER_SAVE_PRODUCT, notes = SWAGGER_SAVE_PRODUCT)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/")
	public ResponseEntity<String> saveProducto(
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			@RequestBody(required = false) @Valid ProductoDto producto
			) throws ProductoException, UnauthorizedException{	
				
		String result="";
		
		try {
			 // String username=utils.getSamaccountname(token);		
			result= productoService.save(producto);
			   
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_producto());
			throw e;
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_producto());
			throw e;
		}
//		catch(UnauthorizedException e) {
//			e.setSubject(propertiesMsg.getLogger_error_executing_save_producto());
//			throw e;
//		}
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
	public ResponseEntity<List<ProductoPageDto>> getProductosPaginated(
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			    @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam Integer idCompania,
	            @RequestParam Integer idNegocio,
	            @RequestParam Integer idRamo,
	            @RequestParam String nemotecnico,
	            @RequestParam String descripcion
	            
			) throws ProductoException, UnauthorizedException{	
				
		List<ProductoPageDto> list=null;
		
		try {
			 // String username=utils.getSamaccountname(token);	
		       	list = productoService.findAllPaginated(page,size,idCompania,idNegocio,idRamo,nemotecnico,descripcion);
			 
			   
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw e;
		}
//		catch(UnauthorizedException e) {
//			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
//			throw e;
//		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw ex;
		}		

		return ResponseEntity.ok(list);
	}	
	


	

}
