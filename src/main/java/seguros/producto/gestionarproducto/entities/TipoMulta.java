package seguros.producto.gestionarproducto.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	
}
