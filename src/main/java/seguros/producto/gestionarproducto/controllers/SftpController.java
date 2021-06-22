package seguros.producto.gestionarproducto.controllers;



import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import seguros.producto.gestionarproducto.dto.RutaFichero;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.services.SftpService;
import seguros.producto.gestionarproducto.servicesImpl.SftpException;
import java.util.List;
import static seguros.producto.gestionarproducto.utils.Constants.HEADER_AUTHORIZACION_KEY;
import static seguros.producto.gestionarproducto.utils.Constants.TOKEN_BEARER_PREFIX;



@RestController
@RequestMapping("/sftp")
@Api(value="Sftp Resource")
@RefreshScope
@CrossOrigin(origins = "${domains.origin.allowed.gestionarproducto}", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.DELETE})
@PreAuthorize("hasRole( @generalProps.getROLE_FUNCIONAL() ) OR  hasRole( @generalProps.getROLE_APROBADOR() ) OR hasRole( @generalProps.getROLE_CONTINUIDAD_OPERATIVA() ) ")
public class SftpController {

	public static final String SWAGGER_POST_UPLOAD_FILE = "Sube un archivo hacia un servidor sftp";
	private static final String MSG_HTTP200 = "Operaci\u00f3n exitosa";
	private static final String MSG_HTTP400 = "Formato de petici\u00f3n erroneo";
	private static final String MSG_HTTP401 = "No autorizado";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String MSG_ERROR_UPLOAD = "Error subiendo fichero";
	private static final String MSG_ERROR_DOWNLOAD = "Error descargando fichero";
	private static final String ATTACHMENT_FILE= "attachment; filename=";
	

	@Autowired
	SftpService sftpService;	
	
	
	@ApiOperation(value = MSG_ERROR_UPLOAD, notes = MSG_ERROR_UPLOAD)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = RutaFichero[].class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@PostMapping(value = "/upload", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity< List<RutaFichero>> uploadFile(
			@RequestPart("files") List<MultipartFile> files,
			@RequestParam("remotePath") String remotePath,
			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) String token
			)  throws SftpException{
		
		List<RutaFichero> result=null;
		
		try {
			
			 result= sftpService.uploadFile(files, remotePath, token.replace(TOKEN_BEARER_PREFIX, ""));			
		}
		catch(SftpException e) {
			e.setSubject(MSG_ERROR_UPLOAD);			
			throw e;
		}
		
		catch(Exception e) {
			SftpException ex= new SftpException(e);
			ex.setSubject(MSG_ERROR_UPLOAD);			
			throw ex;
			
		}
		
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = MSG_ERROR_DOWNLOAD, notes = MSG_ERROR_DOWNLOAD)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = MSG_HTTP200, response = RutaFichero[].class),
		@ApiResponse(code = 401, message = MSG_HTTP400, response = ExceptionResponse.class),
		@ApiResponse(code = 400, message = MSG_HTTP401, response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = MSG_HTTP500, response = ExceptionResponse.class) 
	})
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity< byte[]> donwloadFile(
			@RequestParam("remotePath") String remotePath,
			@RequestHeader(value = HEADER_AUTHORIZACION_KEY, required = true) String token
			)  throws SftpException{
		
		  byte[] file= null;
		  HttpHeaders header = new HttpHeaders();
		
		try {
			  String filename = FilenameUtils.getName(remotePath);
		      header.add(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILE+filename);
			file= sftpService.downloadFile( remotePath,token.replace(TOKEN_BEARER_PREFIX, "") );			
		}
		catch(SftpException e) {
			e.setSubject(MSG_ERROR_DOWNLOAD);			
			throw e;
		}
		
		catch(Exception e) {
			SftpException ex= new SftpException(e);
			ex.setSubject(MSG_ERROR_DOWNLOAD);			
			throw ex;
			
		}
		
	 	return ResponseEntity.
				 ok()
				.headers(header) 
				.contentLength(file.length)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(file);
	}
}
