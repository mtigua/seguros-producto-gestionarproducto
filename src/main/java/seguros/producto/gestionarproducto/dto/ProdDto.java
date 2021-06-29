package seguros.producto.gestionarproducto.dto;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class ProdDto  {

		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank(message = "El campo nombre del producto es requerido")
		private String nombre;

		@NotBlank(message = "El campo nemo del producto es requerido")
		private String nemo;

}
