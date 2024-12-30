package seguros.producto.gestionarproducto.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import seguros.producto.gestionarproducto.configuration.PropertiesMsg;
import seguros.producto.gestionarproducto.dto.GrupoMejorOfertaRequestDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.ForbiddenException;
import seguros.producto.gestionarproducto.exceptions.ResourceNotFoundException;
import seguros.producto.gestionarproducto.services.ProductoService;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value="Grupo Oferta")
@RefreshScope
@RequestMapping("/grupomejoroferta")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ")
public class GrupoOfertaController {

	private static final String SWAGGER_SAVE_PLAN_GRUPO_OFERTA_BY_PRODUCT = "Registrar planes grupo oferta dado un producto";

	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	
	@Autowired
	private PropertiesMsg propertiesMsg;

	@Autowired
	private ProductoService productoService;

	@ApiOperation(value = SWAGGER_SAVE_PLAN_GRUPO_OFERTA_BY_PRODUCT, notes = SWAGGER_SAVE_PLAN_GRUPO_OFERTA_BY_PRODUCT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@PostMapping("/{id}/producto")
	public ResponseEntity<String> saveMejorOfertaByProduct(
			@PathVariable("id") Long id,
			@RequestBody @Valid List<GrupoMejorOfertaRequestDto> gruposAOfrecer
	) throws ProductoException, ResourceNotFoundException, ForbiddenException {
		try {
			productoService.saveGrupoAOfrecerByProduct(id,gruposAOfrecer);
		}
		catch(ResourceNotFoundException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_plan_grupo_oferta_by_product());
			throw e;
		}
		catch(ForbiddenException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_plan_grupo_oferta_by_product());
			throw e;
		}
		catch(ProductoException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_save_plan_grupo_oferta_by_product());
			throw e;
		}
		catch (Exception e) {
			ProductoException ex = new ProductoException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_save_plan_grupo_oferta_by_product());
			throw ex;
		}
		return ResponseEntity.ok(MSG_HTTP200);
	}

}
