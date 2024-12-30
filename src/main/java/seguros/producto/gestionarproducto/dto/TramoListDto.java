package seguros.producto.gestionarproducto.dto;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class TramoListDto  {
	
	
	private Long id;		
	
	private TipoTasaDto tipoTasa; 	

	private TipoTramoDto tipoTramo;
	
	private TarifaEsDto tarifaEs;
	
	private PrimaSobreQueDto tramoPara;

	private String moneda;		
	
	private BigDecimal valorDesde;
	
	private BigDecimal valorHasta;	
	
	private BigDecimal valor;
	
	
    
}
