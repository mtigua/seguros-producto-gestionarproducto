package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Entity(name = "tramo_cobertura")
@Data
public class TramoCobertura {
	
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

	@Column(name = "id_cobertura")
	private Long idCobertura;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	/*
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumns({
			@JoinColumn(name = "id_cobertura", referencedColumnName = "id_cobertura"),
			@JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	})
	private CoberturaProducto cobertura;
	*/


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
