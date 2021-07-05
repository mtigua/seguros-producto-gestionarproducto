package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;
import seguros.producto.gestionarproducto.validators.DetallePromocionConstraint;

@Data
@DetallePromocionConstraint
public class DetallePromocionDto {
	private Long id;	
    
	@NotNull(message = "El tipo de promocion es requerido")
	private Long idTipoPromocion;	
	
	private Long idProductoEnPromocion;
	private Integer montoGiftCard;
	private Integer cuotasDesde;
	private Integer cuotasHasta;
	private Integer tramoPuntos;
	private Boolean despachoADomicilio;
	private Boolean entregaEnTienda;
}
