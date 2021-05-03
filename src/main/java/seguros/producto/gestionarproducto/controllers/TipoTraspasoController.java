package seguros.producto.gestionarproducto.controllers;



import static seguros.producto.gestionarproducto.utils.Constants.HEADER_AUTHORIZACION_KEY;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.dto.TipoTraspasoDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;
import seguros.producto.gestionarproducto.services.TipoTraspasoService;
import seguros.producto.gestionarproducto.servicesImpl.TipoTraspasoException;
import seguros.producto.gestionarproducto.utils.Utils;

@RestController
@Api(value="Tipo traspaso Resource")
@RefreshScope
@RequestMapping("/tipoTraspaso")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ") 
public class TipoTraspasoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TipoTraspasoController.class.getSimpleName());

	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_TIPO_TRASPASO = "Listar tipo de traspaso";
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private TipoTraspasoService tipoTraspasoService;
	
	@Autowired
	private Utils utils;
	
	
	@ApiOperation(value = SWAGGER_GET_TIPO_TRASPASO, notes = SWAGGER_GET_TIPO_TRASPASO)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/")
	public ResponseEntity<List<TipoTraspasoDto>> getTipoTraspaso(
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			) throws TipoTraspasoException, UnauthorizedException{	
				
		List<TipoTraspasoDto> lista= null;
		
		try {
			//  String username=utils.getSamaccountname(token);		
			  lista= tipoTraspasoService.findAll();
			   
		}
		catch(TipoTraspasoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_tipo_traspaso());
			throw e;
		}
//		catch(UnauthorizedException e) {
//			e.setSubject(propertiesMsg.getLogger_error_executing_get_tipo_traspaso());
//			throw e;
//		}
		catch (Exception e) {
			TipoTraspasoException ex = new TipoTraspasoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_tipo_traspaso());
			throw ex;
		}		

		return ResponseEntity.ok(lista);
	}	
	


	

}
