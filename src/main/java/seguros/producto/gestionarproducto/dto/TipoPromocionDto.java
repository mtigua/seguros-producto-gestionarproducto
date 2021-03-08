package seguros.producto.gestionarproducto.dto;



import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TipoPromocionDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de tiene promocion es requerido")
		private String nombre;
		
		private String descripcion;	
    
}
