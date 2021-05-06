package seguros.producto.gestionarproducto.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity(name = "dato_complementario")
@Data
public class DatoComplementario  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nombre;
	
	private String descripcion;	
	
	@Column(nullable =false)
	private String url;
	
    @Column(nullable =false)
	private String expression;
    
}
