package seguros.producto.gestionarproducto.utils;

import java.util.List;
import java.util.Arrays;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Utils {


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
