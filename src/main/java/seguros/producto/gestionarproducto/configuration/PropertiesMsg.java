package seguros.producto.gestionarproducto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="msg")
public class PropertiesMsg {
	
	
	private String logger_error_executing_get_productos;
	
		 
}