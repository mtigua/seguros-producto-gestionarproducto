package seguros.producto.gestionarproducto.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.google.gson.JsonObject;


@RestControllerAdvice
public class ExceptionHandlerControllerAdvice  extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class.getSimpleName());
	
	private static final String FIELD_ERROR="error";
	private static final String FIELD_SUBJECT="subject";
	private static final String MSG_HTTP500 = "Error interno del sistema";
	private static final String MSG_HTTP400 = "Formato de petici\u00F3n err\u00F3neo";

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ExceptionResponse> handleException(final CommonException e,
			final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(e.getErrorMessage());
		JsonObject details = new JsonObject();
		details.addProperty(FIELD_ERROR, e.getDetail());
		details.addProperty(FIELD_SUBJECT, e.getSubject());
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		logger.error(e.getDetail());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ExceptionResponse> handleException(final UnauthorizedException e, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(e.getErrorMessage());
		JsonObject details = new JsonObject();
		details.addProperty(FIELD_ERROR, e.getDetail());
		details.addProperty(FIELD_SUBJECT, e.getSubject());
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		logger.error(e.getDetail());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}	
	
	 @Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		 ExceptionResponse error = new ExceptionResponse();	
			error.setErrorMessage(ex.getClass().toString() + " " + ex.getMessage());
			JsonObject details = new JsonObject();
			details.addProperty(FIELD_ERROR, ex.getMessage());	
			details.addProperty(FIELD_SUBJECT, MSG_HTTP400);
			error.setDetails(details);
			error.setRequestedURI(((ServletWebRequest)request).getRequest().getRequestURI().toString());
			logger.error(ex.getMessage());
			return new ResponseEntity<Object>(error, headers, HttpStatus.NOT_FOUND);
	}

	@Override
	 protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
		 ExceptionResponse error = new ExceptionResponse();
			error.setErrorMessage(ex.getClass().toString() + " " + ex.getMessage());
			JsonObject details = new JsonObject();
			details.addProperty(FIELD_ERROR, ex.getMessage());
			details.addProperty(FIELD_SUBJECT, MSG_HTTP400);
			error.setDetails(details);
			error.setRequestedURI(((ServletWebRequest)request).getRequest().getRequestURI().toString());
			logger.error(ex.getMessage());
			return new ResponseEntity<Object>(error, headers, HttpStatus.BAD_REQUEST);
	  }

	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
		  ExceptionResponse error = new ExceptionResponse();
		  error.setErrorMessage(ex.getClass().toString() + " " + ex.getBindingResult().toString());
			JsonObject details = new JsonObject();
			details.addProperty(FIELD_ERROR, ex.getBindingResult().toString());
			details.addProperty(FIELD_SUBJECT, MSG_HTTP400);
			error.setDetails(details);
			error.setRequestedURI(((ServletWebRequest)request).getRequest().getRequestURI().toString());
			logger.error(ex.getBindingResult().toString());
	    return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	  }

	  @Override
      protected ResponseEntity<Object> handleHttpMessageNotReadable(   HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		  ExceptionResponse error = new ExceptionResponse();
		  error.setErrorMessage(ex.getClass().toString() + " " + ex.getMessage().toString());
			JsonObject details = new JsonObject();
			details.addProperty(FIELD_ERROR, ex.getMessage().toString());
			details.addProperty(FIELD_SUBJECT, MSG_HTTP400);
			error.setDetails(details);
			error.setRequestedURI(((ServletWebRequest)request).getRequest().getRequestURI().toString());
			logger.error(ex.getMessage().toString());
	    return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
      }
	  
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleGenericExceptions(final Exception e, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
		JsonObject details = new JsonObject();
		details.addProperty(FIELD_ERROR, e.getMessage());
		details.addProperty(FIELD_SUBJECT, MSG_HTTP500);
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		logger.error(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
