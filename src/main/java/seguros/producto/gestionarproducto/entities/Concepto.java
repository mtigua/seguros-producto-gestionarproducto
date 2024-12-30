package seguros.producto.gestionarproducto.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Entity(name = "concepto")
@Data
public class Concepto  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	
	
	@Column(unique = true)
	private String name;
	
	private String descripcion;	
	
}
