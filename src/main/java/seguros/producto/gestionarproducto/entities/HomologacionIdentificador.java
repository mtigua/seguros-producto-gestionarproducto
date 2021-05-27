package seguros.producto.gestionarproducto.entities;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
    
}
