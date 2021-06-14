package seguros.producto.gestionarproducto.dto;

import lombok.Data;

@Data
public class OrdenCoberturaDTO {
	private Integer fromIndexRow;
	private Integer toIndexRow;
	private Integer orden;
	private Long idProducto;
}