package seguros.producto.gestionarproducto.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;



@Data
public class FormDataInicioDto {


	private Long id;

	@NotNull(message =  "El campo idCiaNegocioRamo es requerido")
	private Integer idCiaNegocioRamo;		
	
	@NotNull(message =  "El campo ramo es requerido")
	private Integer idRamo;

	@NotNull(message =  "El campo negocio es requerido")
	private Integer idNegocio;

	@NotNull(message =  "El campo compania es requerido")
	private Integer idCompania;
	
	@NotBlank(message =  "El campo descripcion plan es requerido")
	private String  descripcionPlan;
	
	@NotBlank(message =  "El campo nombre es requerido")
	@Size(max=30)
	private String descrip;
	
	private Long[] canales= {4L};
	



}