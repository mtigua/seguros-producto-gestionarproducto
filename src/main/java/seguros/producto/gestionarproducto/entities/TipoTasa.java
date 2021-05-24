package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.*;


@Entity(name = "tipo_tasa")
@Data
public class TipoTasa {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String descripcion;
	
    
}
