package seguros.producto.gestionarproducto.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoberturaProductoDto {

	private Long idCobertura;
	private String nombreCobertura;
	private String nombreDeducible;
	private String iva;
	private String nombreTipo;
	private BigDecimal tasa;
	private String en;
	private BigDecimal valorPrima;
	private String montoAsegurado;
	private Integer orden;

}
