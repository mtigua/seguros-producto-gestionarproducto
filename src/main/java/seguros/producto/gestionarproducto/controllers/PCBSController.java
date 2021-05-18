package seguros.producto.gestionarproducto.controllers;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import seguros.producto.gestionarproducto.dto.*;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.services.PcbsService;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;

@RestController
@Api(value = "PCBS Resource")
@RefreshScope
@RequestMapping("/pcbs")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.DELETE})
//@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ") 
public class PCBSController {

	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_Moneda = "Listar monedas";
	private static final String SWAGGER_GET_Compania = "Listar companias";
	private static final String SWAGGER_GET_Negocio_Por_Compania = "Listar negocios dado el id de la compania";
	private static final String SWAGGER_GET_Ramo_Por_Compania_Negocio = "Listar ramos dado el id de la compania y del negocio";
	private static final String SWAGGER_GET_VALIDAR_CODIGO_POS = "Valida si existe el codigo pos";

	private static final String SWAGGER_GET_Subtipo_Por_Compania_Ramo = "Listar subtipos dado la compania y el ramo";
	private static final String SWAGGER_GET_Producto_Por_Subtipo = "Listar productos dado el subtipo";
	private static final String SWAGGER_GET_Grupo_Matriz = "Listar grupos matriz";
	private static final String SWAGGER_GET_Grupo = "Listar grupos";
	private static final String SWAGGER_GET_Equivalencia_Seguros = "Listar equivalencias de seguros Bigsa dados la compania, el negocio y el ramo";
	private static final String SWAGGER_GET_Grupo_Mejor_Oferta = "Listar grupos de mejor oferta";
	private static final String SWAGGER_GET_BUSCAR_POR_RUT = "Buscar por rut";
	private static final String SWAGGER_GET_LISTAR_ASOCIADO = "Listar asociado";
	private static final String SWAGGER_GET_LISTAR_ASOCIADO_EMISION = "Listar asociado emision";
	private static final String SWAGGER_GET_LISTAR_CATALOGO_CUOTAS = "Listar catalogo cuotas";
	private static final String SWAGGER_GET_BUSCAR_PRODUCT_MANAGER_POR_RUT = "Buscar product manager por rut";
	private static final String SWAGGER_DESENCRIPTAR_PALABRAPASE_PRODUCT_MANAGER = "Desencriptar y validar password de product manager dado rut y password";

	
	
	
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private PcbsService pcbsService;
	
	
	
	@ApiOperation(value = SWAGGER_GET_Moneda, notes = SWAGGER_GET_Moneda)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/moneda")
	public ResponseEntity<List<MonedaDto>> getMoneda(

			) throws PcbsException {
				
		List<MonedaDto> lista= null;
		
		try {
			  lista= pcbsService.findAllMoneda();
			   
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_moneda());
			throw e;
		}

		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_moneda());
			throw ex;
		}		

		return ResponseEntity.ok(lista);
	}	
	

	@ApiOperation(value = SWAGGER_GET_Compania, notes = SWAGGER_GET_Compania)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/compania")
	public ResponseEntity<List<CompaniaDto>> getCompania() throws PcbsException {
		List<CompaniaDto> lista= null;
		
		try {
			  lista= pcbsService.findAllCompania();
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_compania());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_compania());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}	
	
	@ApiOperation(value = SWAGGER_GET_Negocio_Por_Compania, notes = SWAGGER_GET_Negocio_Por_Compania)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/compania/{idCompania}/negocio")
	public ResponseEntity<List<NegocioDto>> getNegocioPorIdCompania(
			@PathVariable("idCompania") @NotNull Long idCompania	
		) throws PcbsException {
		
		List<NegocioDto> lista= null;
		
		try {
			  lista= pcbsService.findAllNegocioByCompania(idCompania);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_negocio_por_compania());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_negocio_por_compania());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}
	
	@ApiOperation(value = SWAGGER_GET_Ramo_Por_Compania_Negocio, notes = SWAGGER_GET_Ramo_Por_Compania_Negocio)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/compania/{idCompania}/negocio/{idNegocio}/ramo")
	public ResponseEntity<List<RamoDto>> getNegocioPorIdCompaniaIdNegocio(
			@PathVariable("idCompania") @NotNull Long idCompania,
			@PathVariable("idNegocio") @NotNull Long idNegocio	
		) throws PcbsException {
		
		List<RamoDto> lista= null;
		
		try {
			  lista= pcbsService.findAllRamoByCompaniaNegocio(idCompania, idNegocio);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_ramo_por_compania_negocio());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_ramo_por_compania_negocio());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_GET_Ramo_Por_Compania_Negocio, notes = SWAGGER_GET_Ramo_Por_Compania_Negocio)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/buscarPoliza")
	public ResponseEntity<Integer> findNumPoliza(
			@RequestParam("poliza") @NotNull String numPoliza,
			@RequestParam("digito") @NotNull String digito,

			/*default parameter*/
			@RequestParam("idCompania") @NotNull Long idCompania,
			@RequestParam("idNegocio") @NotNull Long idNegocio,
			@RequestParam("idRamo") @NotNull Long idRamo

	) throws PcbsException {

		Integer existe = null;

		try {
			existe = pcbsService.findNumPoliza(numPoliza, digito, idCompania, idNegocio, idRamo);
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
			throw ex;
		}
		return ResponseEntity.ok(existe);
	}

	@ApiOperation(value = SWAGGER_GET_VALIDAR_CODIGO_POS, notes = SWAGGER_GET_VALIDAR_CODIGO_POS)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/validarCodigoPos")
	public ResponseEntity<Integer> findCodigoPos(
			@RequestParam("codigoPos") @NotNull String codigoPos

	) throws PcbsException {

		Integer existe = null;

		try {
			existe = pcbsService.findCodigoPos(codigoPos);
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_codigopos());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_codigopos());
			throw ex;
		}
		return ResponseEntity.ok(existe);
	}

	@ApiOperation(value = SWAGGER_GET_BUSCAR_POR_RUT, notes = SWAGGER_GET_BUSCAR_POR_RUT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/buscarRut")
	public ResponseEntity<List<RutDto>> findBuscarRut(
			@RequestParam("numRut") @NotNull String numRut,
			@RequestParam("digito") @NotNull String digito
	) throws PcbsException  {

		List<RutDto> nombres = null;

		try {
			nombres = pcbsService.findNumRut(numRut, digito);
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_rut());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_rut());
			throw ex;
		}
		return ResponseEntity.ok(nombres);
	}

	@ApiOperation(value = SWAGGER_GET_BUSCAR_POR_RUT, notes = SWAGGER_GET_BUSCAR_POR_RUT)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/listarPlanCobertura")
	public ResponseEntity<List<PlanCoberturaDto>> listPlanCobertura(
			@RequestParam("numRamo") @NotNull Long numRamo
	) throws PcbsException {

		List<PlanCoberturaDto> planCoberturaDtos = null;

		try {
			planCoberturaDtos = pcbsService.listPlan(numRamo);
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_list_plan_cobertura());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_list_plan_cobertura());
			throw ex;
		}
		return ResponseEntity.ok(planCoberturaDtos);
	}

	@ApiOperation(value = SWAGGER_GET_LISTAR_ASOCIADO, notes = SWAGGER_GET_LISTAR_ASOCIADO)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/listarWsAsociado")
	public ResponseEntity<List<AsociadoDto>> getWsAsociado(
	) throws PcbsException {

		List<AsociadoDto> asociados = null;

		try {
			asociados = pcbsService.getAsociados();
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_asociado());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_asociado());
			throw ex;
		}
		return ResponseEntity.ok(asociados);
	}

	@ApiOperation(value = SWAGGER_GET_LISTAR_ASOCIADO_EMISION, notes = SWAGGER_GET_LISTAR_ASOCIADO_EMISION)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/listarWsAsociadoEmision")
	public ResponseEntity<List<AsociadoDto>> getWsAsociadoEmision(
	) throws PcbsException {

		List<AsociadoDto> asociados = null;

		try {
			asociados = pcbsService.getAsociadosEmision();
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_asociado());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_asociado());
			throw ex;
		}
		return ResponseEntity.ok(asociados);
	}

	@ApiOperation(value = SWAGGER_GET_LISTAR_CATALOGO_CUOTAS, notes = SWAGGER_GET_LISTAR_CATALOGO_CUOTAS)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/listarCantidadCuotas")
	public ResponseEntity<List<CatalogoCantidadCuotasDto>> getCantidadCuotas(
	) throws PcbsException {

		List<CatalogoCantidadCuotasDto> cantidadCuotasDtos = null;

		try {
			cantidadCuotasDtos = pcbsService.getCatalogoCantidadCuotas();
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_catalogo_cuotas());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_catalogo_cuotas());
			throw ex;
		}
		return ResponseEntity.ok(cantidadCuotasDtos);
	}

	@ApiOperation(value = SWAGGER_GET_LISTAR_CATALOGO_CUOTAS, notes = SWAGGER_GET_LISTAR_CATALOGO_CUOTAS)
	@ApiResponses({
			@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
			@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
	@GetMapping("/listarCantidadCuotasWebPay")
	public ResponseEntity<List<CatalogoCantidadCuotasDto>> getCantidadCuotasWebPay(
	) throws PcbsException {

		List<CatalogoCantidadCuotasDto> cantidadCuotasDtos = null;

		try {
			cantidadCuotasDtos = pcbsService.getCatalogoCantidadCuotasWebPay();
		} catch (PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_catalogo_cuotas());
			throw e;
		} catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_catalogo_cuotas());
			throw ex;
		}
		return ResponseEntity.ok(cantidadCuotasDtos);
	}

	@ApiOperation(value = SWAGGER_GET_Subtipo_Por_Compania_Ramo, notes = SWAGGER_GET_Subtipo_Por_Compania_Ramo)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/compania/{idCompania}/ramo/{idRamo}/subtipo")
	public ResponseEntity<List<SubtipoDto>> getSubtipoPorTipo(
			@PathVariable("idCompania") @NotNull Long idCompania,
			@PathVariable("idRamo") @NotNull Long idRamo				
		) throws PcbsException {
		List<SubtipoDto> lista= null;
		
		try {
			  lista= pcbsService.findAllSubtipoByCompaniaRamo(idCompania,idRamo);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_subtipo_por_compania_ramo());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_subtipo_por_compania_ramo());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}
	
	@ApiOperation(value = SWAGGER_GET_Producto_Por_Subtipo, notes = SWAGGER_GET_Producto_Por_Subtipo)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/subtipo/{codigoSubTipo}/producto")
	public ResponseEntity<List<ProdDto>> getProductoPorSubTipo(@PathVariable("codigoSubTipo") @NotBlank String codigoSubTipo) throws PcbsException{
		List<ProdDto> lista= null;
		
		try {
			  lista= pcbsService.findAllProductoBySubtipo(codigoSubTipo);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_producto_por_subtipo());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException();
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_producto_por_subtipo());
			ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
			ex.setDetail(e.getLocalizedMessage());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}
	
	@ApiOperation(value = SWAGGER_GET_Grupo_Matriz, notes = SWAGGER_GET_Grupo_Matriz)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/subtipo/{codigoSubTipo}/producto/{codigoProducto}/grupomatriz")
	public ResponseEntity<List<GrupoMatrizDto>> getGrupoMatriz(
			@PathVariable("codigoSubTipo") @NotBlank String codigoSubTipo,
			@PathVariable("codigoProducto") @NotBlank String codigoProducto
		) throws PcbsException {
		List<GrupoMatrizDto> lista= null;
		
		try {
			  lista= pcbsService.findAllGrupoMatriz(codigoSubTipo,codigoProducto);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_grupo_matriz());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_grupo_matriz());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}
	
	@ApiOperation(value = SWAGGER_GET_Grupo, notes = SWAGGER_GET_Grupo)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/grupo")
	public ResponseEntity<List<GrupoDto>> getGrupo() throws PcbsException {
		List<GrupoDto> lista= null;
		
		try {
			  lista= pcbsService.findAllGrupo();
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_grupo());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_grupo());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}
	
	@ApiOperation(value = SWAGGER_GET_Equivalencia_Seguros, notes = SWAGGER_GET_Equivalencia_Seguros)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/compania/{idCompania}/negocio/{idNegocio}/ramo/{idRamo}/equivalenciaseguro")
	public ResponseEntity<List<EquivalenciaSeguroDto>> getEquivalenciaPorCompaniaNegocioRamo(
			@PathVariable("idCompania") @NotNull Long idCompania,
			@PathVariable("idNegocio") @NotNull Long idNegocio,
			@PathVariable("idRamo") @NotNull Long idRamo
		) throws PcbsException {
		
		List<EquivalenciaSeguroDto> lista= null;
		
		try {
			  lista= pcbsService.findAllEquivalenciaSeguro(idCompania, idNegocio, idRamo);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_equivalencia_seguro());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_equivalencia_seguro());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = SWAGGER_GET_Grupo_Mejor_Oferta, notes = SWAGGER_GET_Grupo_Mejor_Oferta)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/grupomejoroferta")
	public ResponseEntity<List<GrupoMejorOfertaDto>> getGrupoMejorOferta() throws PcbsException {
		List<GrupoMejorOfertaDto> lista= null;
		
		try {
			  lista= pcbsService.findAllGrupoMejorOferta();
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_grupo_mejor_oferta());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_grupo_mejor_oferta());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}


	@ApiOperation(value = SWAGGER_GET_BUSCAR_PRODUCT_MANAGER_POR_RUT, notes = SWAGGER_GET_BUSCAR_PRODUCT_MANAGER_POR_RUT)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/buscarRutProductManager")
	public ResponseEntity<String> findRutProductManager(
			@RequestParam("rut") @NotNull String numRut
		) throws PcbsException {

		System.out.println("Ariel "+numRut);
		
		
		String rut= "";

		try {
			rut= pcbsService.findRutProductManager(numRut);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_rut_product_manager());
			throw e;
		}

		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_rut_product_manager());
			throw ex;
		}
		return ResponseEntity.ok(rut);
	}

	@ApiOperation(value = SWAGGER_DESENCRIPTAR_PALABRAPASE_PRODUCT_MANAGER, notes = SWAGGER_DESENCRIPTAR_PALABRAPASE_PRODUCT_MANAGER)
	@ApiResponses({
		@ApiResponse(code = 200, message = MSG_HTTP200, response = String.class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class)
	})
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/desencriptar")
	public ResponseEntity<Integer> desencriptar(
			@RequestParam("rut") @NotNull String numRut,
			@RequestParam("password") @NotNull String password
		) throws PcbsException {

		Integer valid= 0;

		try {
			valid= pcbsService.decryptPasswordProductManager(numRut,password);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_decrypt_password_product_manager());
			throw e;
		}

		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_decrypt_password_product_manager());
			throw ex;
		}
		return ResponseEntity.ok(valid);
	}
	
	
	
}
