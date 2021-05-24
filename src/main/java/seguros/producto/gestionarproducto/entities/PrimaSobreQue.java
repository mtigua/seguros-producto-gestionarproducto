package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.*;


@Entity(name = "prima_sobre_que")
@Data
public class PrimaSobreQue {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String descripcion;
	
    
}
