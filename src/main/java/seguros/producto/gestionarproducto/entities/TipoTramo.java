package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.*;


@Entity(name = "tipo_tramo")
@Data
public class TipoTramo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String descripcion;
	
}
