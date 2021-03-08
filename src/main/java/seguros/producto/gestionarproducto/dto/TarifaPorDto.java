package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TarifaPorDto  {
	
     private Long id;
	
	@NotBlank(message = "El campo nombre de tarifa por es requerido")
	private String nombre;
	
	private String descripcion;	  
    
}
