package seguros.producto.gestionarproducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableAsync
@EnableWebMvc
public class ProductoApplication {		 
	
	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
	}

}
