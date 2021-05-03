package seguros.producto.gestionarproducto.utils;

import static seguros.producto.gestionarproducto.utils.Constants.DISPLAY_NAME;
import static seguros.producto.gestionarproducto.utils.Constants.TOKEN_BEARER_PREFIX;
import static seguros.producto.gestionarproducto.utils.Constants.USERNAME;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import seguros.producto.gestionarproducto.configuration.Properties;
import seguros.producto.gestionarproducto.exceptions.UnauthorizedException;

@Component
public class Utils {

	@Autowired
	private Properties properties;
	
	
	 public static String getCurrentDate() {
		    Calendar cal = Calendar.getInstance(); 			   
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); 
	    	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    	return dateFormat.format(cal.getTime());		
	  }
	 
	 
	 public  String getSamaccountname(String token) throws UnauthorizedException{
		 String username = null;
		
		 try {
		    String jwtToken = token;			
			String secret= properties.getSecret();
			Object objUsername = Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(jwtToken.replace(TOKEN_BEARER_PREFIX, "")).getBody().get(USERNAME);
			Object objDisplayName = Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(jwtToken.replace(TOKEN_BEARER_PREFIX, "")).getBody().get(DISPLAY_NAME);

			if (objUsername!=null && objDisplayName!=null) {
				username = objUsername.toString() + " - " + objDisplayName.toString();
			}
		 }
		 catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException | UnsupportedEncodingException e) {
			    UnauthorizedException ex = new UnauthorizedException();
				ex.setErrorMessage(e.getClass().toString() + " " + e.getMessage());
				ex.setDetail(e.getCause()!=null?e.getCause().toString():e.getMessage());
				throw ex;
		 }
		return username;
	}	
	
	
	public static Gson buildGson() {
		 GsonBuilder builder = new GsonBuilder();

	        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	          	@Override
				public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
						JsonDeserializationContext context) throws JsonParseException {
					 return new Date(json.getAsJsonPrimitive().getAsLong());
				}
	        });

	        return  builder.create();	       
	}
	
	public static String convertObjectToJson(Object object) throws JsonProcessingException {
	    if (object == null) {
	        return null;
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(object);
	}
	
	
	public static  boolean matcher(String[] whiteList,String url) {
		List<String> lista = Arrays.asList(whiteList);
		boolean match=false;
		int i=0;
		
		while(!match && i<lista.size() ) {			
			
			 if(url.contains(lista.get(i))) {
				 match=true;
			 }
			 else {
				 i++;
			 }
		}
		
	
		return match;
	}

	
}
