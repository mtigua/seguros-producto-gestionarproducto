package seguros.producto.gestionarproducto.controllers;



import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
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
import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.EquivalenciaSeguroDto;
import seguros.producto.gestionarproducto.dto.GrupoDto;
import seguros.producto.gestionarproducto.dto.GrupoMatrizDto;
import seguros.producto.gestionarproducto.dto.GrupoMejorOfertaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.ProdDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.dto.SubtipoDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;
import seguros.producto.gestionarproducto.services.PcbsService;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.utils.Utils;

@RestController
@Api(value="PCBS Resource")
@RefreshScope
@RequestMapping("/pcbs")
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
public class PCBSController {
	
	private static final Logger logger = LoggerFactory.getLogger(PCBSController.class.getSimpleName());

	
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String SWAGGER_GET_Moneda = "Listar monedas";
	private static final String SWAGGER_GET_Compania = "Listar companias";
	private static final String SWAGGER_GET_Negocio_Por_Compania = "Listar negocios dado el id de la compania";
	private static final String SWAGGER_GET_Ramo_Por_Compania_Negocio = "Listar ramos dado el id de la compania y del negocio";
	
	private static final String SWAGGER_GET_Subtipo_Por_Compania_Ramo = "Listar subtipos dado la compania y el ramo";
	private static final String SWAGGER_GET_Producto_Por_Subtipo = "Listar productos dado el subtipo";
	private static final String SWAGGER_GET_Grupo_Matriz = "Listar grupos matriz";
	private static final String SWAGGER_GET_Grupo = "Listar grupos";
	private static final String SWAGGER_GET_Equivalencia_Seguros = "Listar equivalencias de seguros Bigsa dados la compania, el negocio y el ramo";
	private static final String SWAGGER_GET_Grupo_Mejor_Oferta = "Listar grupos de mejor oferta";
	private static final String SWAGGER_GET_BUSCAR_POR_RUT = "Buscar por rut";
	
	
	
	
	
	@Autowired
	private PropertiesMsg propertiesMsg;	
	
	@Autowired
	private PcbsService pcbsService;
	
	@Autowired
	private Utils utils;
	
	
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
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			) throws PcbsException, UnauthorizedException{	
				
		List<MonedaDto> lista= null;
		
		try {
			//  String username=utils.getSamaccountname(token);		
			  lista= pcbsService.findAllMoneda();
			   
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_moneda());
			throw e;
		}
//		catch(UnauthorizedException e) {
//			e.setSubject(propertiesMsg.getLogger_error_executing_get_moneda());
//			throw e;
//		}
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
	public ResponseEntity<List<CompaniaDto>> getCompania() throws PcbsException, UnauthorizedException{	
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
		) throws PcbsException, UnauthorizedException{	
		
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
		) throws PcbsException, UnauthorizedException{	
		
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
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/buscarPoliza")
	public ResponseEntity<Integer> findNumPoliza(		
			@RequestParam("poliza") @NotNull String numPoliza
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
		) throws PcbsException, UnauthorizedException{	
		
		Integer existe= null;
		
		try {
			existe= pcbsService.findNumPoliza(numPoliza);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
			throw e;
		}
//		catch(UnauthorizedException e) {
//		e.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
//		throw e;
//	}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
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
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",required = true, dataType = "string", paramType = "header") })
	@GetMapping("/buscarRut")
	public ResponseEntity<List<String>> findBuscarRut(
			@RequestParam("numRut") @NotNull String numRut,
			@RequestParam("digito") @NotNull String digito
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true)
//			String token
	) throws PcbsException, UnauthorizedException{

		List<String> nombres = null;

		try {
			nombres= pcbsService.findNumRut(numRut, digito);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
			throw e;
		}
//		catch(UnauthorizedException e) {
//		e.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
//		throw e;
//	}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_numpoliza());
			throw ex;
		}
		return ResponseEntity.ok(nombres);
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
		) throws PcbsException, UnauthorizedException{	
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
	public ResponseEntity<List<ProdDto>> getProductoPorSubTipo(@PathVariable("codigoSubTipo") @NotBlank String codigoSubTipo) throws PcbsException, UnauthorizedException{	
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
		) throws PcbsException, UnauthorizedException{	
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
	public ResponseEntity<List<GrupoDto>> getGrupo() throws PcbsException, UnauthorizedException{	
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
		) throws PcbsException, UnauthorizedException{	
		
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
	public ResponseEntity<List<GrupoMejorOfertaDto>> getGrupoMejorOferta() throws PcbsException, UnauthorizedException{	
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
	
	
	@ApiOperation(value = SWAGGER_GET_Ramo_Por_Compania_Negocio, notes = SWAGGER_GET_Ramo_Por_Compania_Negocio)
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
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
		) throws PcbsException, UnauthorizedException{	
		
		String rut= "";
		
		try {
			rut= pcbsService.findRutProductManager(numRut);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_find_rut_product_manager());
			throw e;
		}
//		catch(UnauthorizedException e) {
//		e.setSubject(propertiesMsg.getLogger_error_executing_find_rut_product_manager());
//		throw e;
//	}
		catch (Exception e) {
			PcbsException ex = new PcbsException(e);
			ex.setSubject(propertiesMsg.getLogger_error_executing_find_rut_product_manager());
			throw ex;
		}		
		return ResponseEntity.ok(rut);
	}
}
