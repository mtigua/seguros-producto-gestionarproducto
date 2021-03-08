package seguros.producto.gestionarproducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class ProductoApplication {		 
	
	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
		
	}

}
