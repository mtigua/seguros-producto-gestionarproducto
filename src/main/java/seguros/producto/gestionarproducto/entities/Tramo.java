package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;


@Entity(name = "tramo")
@Data
public class Tramo  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	
	@ManyToOne	  
	@JoinColumn(name = "id_tipo_tasa") 
	private TipoTasa tipoTasa; 
	
	@ManyToOne	  
	@JoinColumn(name = "id_tipo_tramo",nullable = false) 
	private TipoTramo tipoTramo;
	
	@ManyToOne	  
	@JoinColumn(name = "id_tarifa_es",nullable = false) 
	private TarifaEs tarifaEs;

	@ManyToOne	  
	@JoinColumn(name = "id_tramo_para") 
	private PrimaSobreQue tramoPara;
	
	@Column(length = 5, name = "dome_mone_cod")
	private String moneda;
		
	@Column(name = "valor_desde",nullable = false)
	private BigDecimal valorDesde;
	
	@Column(name = "valor_hasta",nullable = false)
	private BigDecimal valorHasta;
	
	
	@Column(name = "valor",nullable = false)
	private BigDecimal valor;
	

    
}
