package seguros.producto.gestionarproducto.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
@ConfigurationProperties(prefix="sql.query")
public class PropertiesSql {
	
	
	private String LISTAR_MONEDAS;
;
	
		 
}