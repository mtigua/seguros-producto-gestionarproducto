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
import seguros.producto.gestionarproducto.dto.ConceptoDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;
import seguros.producto.gestionarproducto.services.ConceptoService;
import seguros.producto.gestionarproducto.servicesImpl.ConceptoException;
import seguros.producto.gestionarproducto.utils.Utils;

@RestController
@Api(value="Concepto Resource")
@RefreshScope
@RequestMapping("/concepto")
public class ConceptoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConceptoController.class.getSimpleName());

	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_Concepto = "Listar conceptos";
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private ConceptoService conceptoService;
	
	@Autowired
	private Utils utils;
	
	
	@ApiOperation(value = SWAGGER_GET_Concepto, notes = SWAGGER_GET_Concepto)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/")
	public ResponseEntity<List<ConceptoDto>> getConcepto(
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			) throws ConceptoException, UnauthorizedException{	
				
		List<ConceptoDto> lista= null;
		
		try {
//			  String username=utils.getSamaccountname(token);		
			  lista= conceptoService.findAll();
			   
		}
		catch(ConceptoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_concepto());
			throw e;
		}
//		catch(UnauthorizedException e) {
//			e.setSubject(propertiesMsg.getLogger_error_executing_get_concepto());
//			throw e;
//		}
		catch (Exception e) {
			ConceptoException ex = new ConceptoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_concepto());
			throw ex;
		}		

		return ResponseEntity.ok(lista);
	}	
	


	

}
