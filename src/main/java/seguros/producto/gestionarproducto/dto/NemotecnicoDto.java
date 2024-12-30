package seguros.producto.gestionarproducto.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class NemotecnicoDto  {
	
	
	private Long id;		

	@NotNull(message = "La compañía es requerida")
	private Integer compania;
	
	@NotNull(message = "El negocio es requerido")
	private Integer negocio;
	
	@NotNull(message = "El ramo es requerido")
	private Integer ramo;
	
	@NotBlank(message =  "El campo descripción es requerido")
	private String  descripcion;
	
	@NotBlank(message =  "El campo nombre es requerido")
	@Size(max=30)
	private String nombre;
	
	@Size(max=4)
	private String nemotecnico;
	
	private Long estado;
	

	
    
}
