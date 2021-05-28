package seguros.producto.gestionarproducto.entities;



import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Entity(name = "canal")
@Data
public class Canal  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String descripcion;	
	
	@ManyToMany( cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
		})
		@JoinTable( name = "datocomplementario_canal",
			joinColumns = @JoinColumn (name = "canal_id"),
			inverseJoinColumns =  @JoinColumn(name = "datocomplementario_id")
		)
	private Set<DatoComplementario> datosComplementarios = new HashSet<>();
		
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	public void addDatoComplementario(DatoComplementario datoComplementario) {
	        this.datosComplementarios.add(datoComplementario);
	 }
		
	public void removeDatoComplementario(DatoComplementario datoComplementario) {
			this.datosComplementarios.remove(datoComplementario);
	}
	
	
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
    
}
