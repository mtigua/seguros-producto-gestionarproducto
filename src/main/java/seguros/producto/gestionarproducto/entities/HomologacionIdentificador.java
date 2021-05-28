package seguros.producto.gestionarproducto.entities;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity(name = "homologacion_identificador")
@Data
public class HomologacionIdentificador  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "termino_origen")
	private String terminoOrigen;	
	
	@Column(name = "termino_destino")
	private String terminoDestino;	
	
	@Column(name = "valor_origen")
	private String valorOrigen;
	
	@Column(name = "valor_destino")
	private String valorDestino;
	
	@ManyToOne
	@JoinColumn(name="id_concepto", nullable=false)
	private Concepto concepto;
	
	
	@ManyToOne
	@JoinColumn(name="id_canal", nullable=false)
	private Canal canal;
	

	
}
