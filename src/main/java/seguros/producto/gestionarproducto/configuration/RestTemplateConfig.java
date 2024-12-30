package seguros.producto.gestionarproducto.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	
	@Autowired
	private Properties properties; 
	
	@Bean 
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) throws Exception { 
		return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(properties.getCONNECTION_TIMEOUT())).setReadTimeout(Duration.ofSeconds(properties.getREAD_TIMEOUT())).build();
	}
	
}
