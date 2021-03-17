package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class ProdDto  {
	
	    private String id;
		
		@NotBlank(message = "El campo nombre del producto es requerido")
		private String nombre;

}
