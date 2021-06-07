package seguros.producto.gestionarproducto.dto;


import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;
import seguros.producto.gestionarproducto.validators.TramoConstraint;

@Data
@TramoConstraint
public class TramoDto  {
	
	
	
	private Long id;	
	
	private Long tipoTasa; 
	
	@NotNull(message = "El tipo de tramo es requerido")
	private Long tipoTramo;
	
	@NotNull(message = "La tarifa es requerido")
	private Long tarifaEs;
	
	private Long tramoPara;

	private String moneda;			
	
	@NotNull(message = "El valor desde es requerido")
	private BigDecimal valorDesde;	
	
	@NotNull(message = "El valor hasta es requerido")
	private BigDecimal valorHasta;	
	
	@NotNull(message = "El valor es requerido")
	private BigDecimal valor;
	
	
	
    
}
