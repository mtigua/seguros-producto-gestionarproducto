package seguros.producto.gestionarproducto.controllers;



import static seguros.producto.gestionarproducto.utils.Constants.HEADER_AUTHORIZACION_KEY;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.dto.CanalDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;
import seguros.producto.gestionarproducto.services.CanalService;
import seguros.producto.gestionarproducto.servicesImpl.CanalException;
import seguros.producto.gestionarproducto.utils.Utils;

@RestController
@Api(value="Canal Resource")
@RefreshScope
@RequestMapping("/canal")
public class CanalController {
	
	private static final Logger logger = LoggerFactory.getLogger(CanalController.class.getSimpleName());

	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_Canal = "Listar canales";
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private CanalService canalService;
	
	@Autowired
	private Utils utils;
	
	
	@ApiOperation(value = SWAGGER_GET_Canal, notes = SWAGGER_GET_Canal)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/")
	public ResponseEntity<List<CanalDto>> getCanal(
			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
			String token
			) throws CanalException, UnauthorizedException{	
				
		List<CanalDto> lista= null;
		
		try {
			  String username=utils.getSamaccountname(token);		
			  lista= canalService.findAll();
			   
		}
		catch(CanalException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw e;
		}
		catch(UnauthorizedException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			throw e;
		}
		catch (Exception e) {
			CanalException ex = new CanalException();
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_productos());
			ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
			ex.setDetail(e.getLocalizedMessage());
			throw ex;
		}		

		return ResponseEntity.ok(lista);
	}	
	


	

}
