package seguros.producto.gestionarproducto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoberturaDTO {
	private Long cobertura;
	private String cobeConsinIva;
	private Integer deducible;
	private Integer edadMaximaIngreso;
	private Integer edadMaximaPermanencia;
	private Long en;
	private String montoAsegurado;
	private BigDecimal montoPrima;
	private String paraParentesco;
	private BigDecimal porcentajeSobreCapitalAsegurado;
	private BigDecimal primaMinima;
	private Long tarifa;
	private Long tarifaValor;
	private BigDecimal tasa;
	private Long tipoCobertura;
	private Long producto;
}