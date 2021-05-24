package seguros.producto.gestionarproducto.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ParentescoDto {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de parentesco es requerido")
		private String nombre;
		
		private String descripcion;	
	
    
}
