package seguros.producto.gestionarproducto.dto;

import java.sql.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;



@Data
public class FormDataEncabezadoSaveDto {


	private Long id;

	@Size(max=4)
	private String nemot;
	
	private Boolean  ventaPos;
	
	private Integer  codigoPos;
	
	@Size(max=50)
	private String idProducto;
	
	@Size(max=50)
	private String  idPlan;
	
	@Size(max=50)
	private String  idDed;
	
	private Date  fechaCierreVtas;
	
	@Size(max=20)
	private String  subtipo;
	
	@Size(max=20)
	private String  producto;	
	
	private Integer ggmCod;
	
	@Size(max=4)
	@NotBlank(message = "El campo moneda es requerido")
	private String domiMoneCod;	
	
	private Integer grupoCodigo;
	
	private Integer equivalenciaSeguros;
	
	private Integer idGrupoMejorOferta;
	
	

}