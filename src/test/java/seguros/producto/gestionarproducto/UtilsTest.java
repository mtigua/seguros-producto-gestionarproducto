package seguros.producto.gestionarproducto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import seguros.producto.gestionarproducto.exceptions.ExceptionResponse;
import seguros.producto.gestionarproducto.utils.Utils;

public class UtilsTest {

	
	@Test
	public void testConvertObjectToJson() throws JsonProcessingException {

         ExceptionResponse exceptionResponse = new ExceptionResponse();
         exceptionResponse.setErrorMessage("Error en el sistema");
         exceptionResponse.setRequestedURI("/producto/");
         
         JsonObject details = new JsonObject();
 		 details.addProperty("error", "No hay conexion a la base de datos");
 		 details.addProperty("subject", "Error listando producto");
 		
         exceptionResponse.setDetails(details);
         
         String parseObjectToString= Utils.convertObjectToJson(exceptionResponse);
         String parseObjectExpected= "{\"errorMessage\":\"Error en el sistema\",\"requestedURI\":\"/producto/\",\"details\":\"{\\\"error\\\":\\\"No hay conexion a la base de datos\\\",\\\"subject\\\":\\\"Error listando producto\\\"}\"}";
         
		assertEquals(parseObjectExpected, parseObjectToString);
	}
	
	@Test
	public void testWhenNullConvertObjectToJson() throws JsonProcessingException {

         ExceptionResponse exceptionResponse = null;
         
         String parseObjectToString= Utils.convertObjectToJson(exceptionResponse);
          
		assertEquals(null, parseObjectToString);
	}

	@Test
	public void testMatcherWhenTrue() {
		 String url= "/swagger-ui.html";
		 
		 boolean result= Utils.matcher(getWhiteList(), url); 
		
		assertEquals(true, result);
	}
	
	@Test
	public void testMatcherWhenFalse() {
		 String url= "/producto/";
		 
		 boolean result= Utils.matcher(getWhiteList(), url); 
		
		assertEquals(false, result);
	}
	
	private String [] getWhiteList() {
		return new String[]  {"/v2/api-docs","/configuration/ui","/swagger-resources","/configuration/security","/swagger-ui.html","/webjars","/health","/refresh"};
	}

}
