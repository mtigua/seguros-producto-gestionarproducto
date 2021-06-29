package seguros.producto.gestionarproducto.dto;


import lombok.Data;

@Data
public class DetallePromocionListDto {
	private Long id;	
    private TipoPromocionDto tipoPromocion;	
	private Long idPromocionProducto;
	private Integer montoGiftCard;
	private Integer cuotasDesde;
	private Integer cuotasHasta;
	private Integer tramoPuntos;
	private Boolean despachoADomicilio;
	private Boolean entregaEnTienda;
}
