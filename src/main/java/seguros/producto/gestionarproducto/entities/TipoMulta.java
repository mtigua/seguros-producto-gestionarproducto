package seguros.producto.gestionarproducto.entities;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Entity(name = "tipo_multa")
@Data
public class TipoMulta  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String descripcion;	
	
	
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
}
