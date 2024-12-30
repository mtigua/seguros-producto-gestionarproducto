package seguros.producto.gestionarproducto.dto;

import lombok.Data;
import seguros.producto.gestionarproducto.validators.CoberturaConstraint;

import java.math.BigDecimal;

@Data
@CoberturaConstraint
public class CoberturaDTO {
	private Long cobertura;
	private String cobeConsinIva;
	private Integer deducible;
	private Integer edadMaximaIngreso;
	private Integer edadMaximaPermanencia;
	private Long en;
	private String montoAsegurado;
	private BigDecimal montoPrima;
	private Long paraParentesco;
	private BigDecimal porcentajeSobreCapitalAsegurado;
	private BigDecimal primaMinima;
	private BigDecimal tarifa;
	private BigDecimal tasa;
	private Long tipoCobertura;
	private Long producto;
	private String ramoName;
}