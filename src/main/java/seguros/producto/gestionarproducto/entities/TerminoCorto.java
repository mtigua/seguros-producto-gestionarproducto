package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
	
	@Column(precision =10 , scale=2, nullable = false)
	private BigDecimal primaPeriodo;
	
	@Column(precision =10 , scale=2, nullable = false)
	private BigDecimal monto;
	
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	
	@Column(nullable = false)
	private Integer cuotas;
	
	@Column(length = 5, name = "dome_mone_cod")
	private String moneda;
		

	
	
    
}
