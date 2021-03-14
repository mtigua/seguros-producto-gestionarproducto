package seguros.producto.gestionarproducto.controllers;



import static seguros.producto.gestionarproducto.utils.Constants.HEADER_AUTHORIZACION_KEY;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import seguros.producto.gestionarproducto.dto.CompaniaDto;
import seguros.producto.gestionarproducto.dto.MonedaDto;
import seguros.producto.gestionarproducto.dto.NegocioDto;
import seguros.producto.gestionarproducto.dto.RamoDto;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;
import seguros.producto.gestionarproducto.services.PcbsService;
import seguros.producto.gestionarproducto.servicesImpl.PcbsException;
import seguros.producto.gestionarproducto.utils.Utils;

@RestController
@Api(value="PCBS Resource")
@RefreshScope
@RequestMapping("/pcbs")
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
	public ResponseEntity<List<MonedaDto>> getCanal(
//			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) 			
//			String token
			) throws PcbsException, UnauthorizedException{	
				
		List<MonedaDto> lista= null;
		
		try {
			//  String username=utils.getSamaccountname(token);		
			  lista= pcbsService.findAllMonedas();
			   
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
			PcbsException ex = new PcbsException();
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_moneda());
			ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
			ex.setDetail(e.getLocalizedMessage());
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
			  lista= pcbsService.findAllCompanias();
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_compania());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException();
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_compania());
			ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
			ex.setDetail(e.getLocalizedMessage());
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
			  lista= pcbsService.findAllNegociosByCompania(idCompania);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_negocio_por_compania());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException();
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_negocio_por_compania());
			ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
			ex.setDetail(e.getLocalizedMessage());
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
			  lista= pcbsService.findAllRamosByCompaniaNegocio(idCompania, idNegocio);
		}
		catch(PcbsException e) {
			e.setSubject(propertiesMsg.getLogger_error_executing_get_ramo_por_compania_negocio());
			throw e;
		}
		catch (Exception e) {
			PcbsException ex = new PcbsException();
			ex.setSubject(propertiesMsg.getLogger_error_executing_get_ramo_por_compania_negocio());
			ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
			ex.setDetail(e.getLocalizedMessage());
			throw ex;
		}		
		return ResponseEntity.ok(lista);
	}	
	


}
