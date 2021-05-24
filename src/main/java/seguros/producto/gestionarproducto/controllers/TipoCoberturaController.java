package seguros.producto.gestionarproducto.controllers;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.dto.TipoCoberturaDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.services.TipoCoberturaService;
import seguros.producto.gestionarproducto.servicesImpl.TipoCoberturaException;

import java.util.List;


@RestController
@Api(value="TipoCobertura Resource")
@RefreshScope
@RequestMapping("/tipocobertura")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ") 
public class TipoCoberturaController {
	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_TipoCobertura = "Listar tipo de cobertura";
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private TipoCoberturaService tipoCoberturaService;
	

	
	
	@ApiOperation(value = SWAGGER_GET_TipoCobertura, notes = SWAGGER_GET_TipoCobertura)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/")
	public ResponseEntity<List<TipoCoberturaDto>> getTipoCobertura(	) throws TipoCoberturaException {
		List<TipoCoberturaDto> lista= null;

		try {
			  lista= tipoCoberturaService.findAll();
		}
		catch(TipoCoberturaException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_tipo_cobertura());
			throw e;
		}
		catch (Exception e) {
			TipoCoberturaException ex = new TipoCoberturaException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_tipo_cobertura());
			throw ex;
		}		

		return ResponseEntity.ok(lista);
	}	
	


	

}
