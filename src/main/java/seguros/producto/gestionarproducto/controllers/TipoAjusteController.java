package seguros.producto.gestionarproducto.controllers;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
import seguros.producto.gestionarproducto.dto.TipoAjusteDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.services.TipoAjusteService;
import seguros.producto.gestionarproducto.servicesImpl.TipoAjusteException;


@RestController
@Api(value="TipoAjuste Resource")
@RefreshScope
@RequestMapping("/tipoajuste")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ") 
public class TipoAjusteController {
	

	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_TipoAjuste = "Listar tipo de ajuste";
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private TipoAjusteService tipoAjusteService;
	
	
	
	@ApiOperation(value = SWAGGER_GET_TipoAjuste, notes = SWAGGER_GET_TipoAjuste)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/")
	public ResponseEntity<List<TipoAjusteDto>> getTipoAjuste() throws TipoAjusteException{	
				
		List<TipoAjusteDto> lista= null;
		
		try {	
			  lista= tipoAjusteService.findAll();
			   
		}
		catch(TipoAjusteException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_tipo_ajuste());
			throw e;
		}

		return ResponseEntity.ok(lista);
	}	
	


	

}
