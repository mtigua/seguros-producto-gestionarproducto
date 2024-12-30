package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.*;



@Entity(name = "parentesco")
@Data
public class Parentesco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	
	private String descripcion;	
	
	
	
}
