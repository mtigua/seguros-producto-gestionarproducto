package seguros.producto.gestionarproducto.entities;



import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import seguros.producto.gestionarproducto.dto.ActionType;
import seguros.producto.gestionarproducto.dto.State;


@Entity(name = "estado_integracion")
@Data
public class EstadoIntegracion  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_plan_de_cobertura")
	private Long idProducto;
	
	 @Enumerated(EnumType.STRING)
	 @Column(name = "tipo_accion")
	 private ActionType tipoAccion;
	
	 @Enumerated(EnumType.STRING)
	 @Column(name = "estado")
	 private State state;
	 
	 @Column(name = "fecha_accion_robot")
	 private Date fechaAccionRobot;
	 
	 @Column(name = "detalle_error")
	 private String detalleError; 
    
	 @ManyToOne
	 @JoinColumn(name = "id_canal")
	 private Canal canal;
	 
	 @Column(name = "fecha_creacion", updatable=false)
	 @CreationTimestamp
	 private LocalDateTime fechaCreacion;
}
