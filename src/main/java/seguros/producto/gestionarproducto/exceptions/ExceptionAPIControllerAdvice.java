package seguros.producto.gestionarproducto.exceptions;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionAPIControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionAPIControllerAdvice.class.getSimpleName());

	private static final String MSG_HTTP404 = "Recurso no encontrado";
	private static final String FIELD_ERROR="error";
	private static final String FIELD_SUBJECT="subject";

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(e.getLocalizedMessage());
		JsonObject details = new JsonObject();
		details.addProperty(FIELD_ERROR, e.getMessage());
		details.addProperty(FIELD_SUBJECT, MSG_HTTP404);
		error.setDetails(details);
		error.setRequestedURI(request.getContextPath());
		logger.error(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
