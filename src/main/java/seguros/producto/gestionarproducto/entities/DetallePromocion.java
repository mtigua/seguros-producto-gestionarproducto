package seguros.producto.gestionarproducto.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity(name = "plan_detalle_promo")
@Data
public class DetallePromocion  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_promocion",nullable = false)
	private  TipoPromocion tipoPromocion;	
	
	@Column(name = "id_producto_en_promocion")
	private Long idProductoEnPromocion;
		
	@Column(name = "monto_giftcard")
	private Integer montoGiftCard;
	
	@Column(name = "cuotas_desde")
	private Integer cuotasDesde;

	@Column(name = "cuotas_hasta")
	private Integer cuotasHasta;

	@Column(name = "tramo_puntos")
	private Integer tramoPuntos;

	@Column(name = "despacho_a_domicilio")
	private Boolean despachoADomicilio;

	@Column(name = "entrega_en_tienda")
	private Boolean entregaEnTienda;
    
}
