package seguros.producto.gestionarproducto.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class PrimaSobreQueDto {

	    private Long id;
		
		@NotBlank(message = "El campo nombre de la prima sobre es requerido")
		private String nombre;
		
		private String descripcion;	

}
