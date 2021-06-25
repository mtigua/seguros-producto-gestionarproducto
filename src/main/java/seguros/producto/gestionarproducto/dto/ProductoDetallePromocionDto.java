package seguros.producto.gestionarproducto.dto;


import java.util.List;

import lombok.Data;



@Data
public class ProductoDetallePromocionDto  {
	
	    private Long id;
	    
	    private Long idTipoPromocion;
	    
	    private List<DetallePromocionListDto> detallePromociones;
	    
		public ProductoDetallePromocionDto() {
		}
	    
		public ProductoDetallePromocionDto(List<DetallePromocionListDto> detallePromociones) {
			this.detallePromociones= detallePromociones;
		}
		

}
