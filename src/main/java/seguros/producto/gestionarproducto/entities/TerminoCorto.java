package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import lombok.Data;


@Entity(name = "termino_corto")
@Data
public class TerminoCorto  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	
	@ManyToOne	  
	@JoinColumn(name = "id_tipo_multa") 
	private TipoMulta tipoMulta;
	 

	
	@Min(1)
	@Column(nullable = false)
	private Integer mesDesde;
	
	
	@Min(1)
	@Column(nullable = false)
	private Integer mesHasta;
	
	@Column(precision =10 , scale=2)
	private BigDecimal primaPeriodo;
	
	@Column(precision =10 , scale=2)
	private BigDecimal monto;
	
	private Integer cuotas;
	
	@Column(length = 5, name = "dome_mone_cod")
	private String moneda;
		

	
    
}
