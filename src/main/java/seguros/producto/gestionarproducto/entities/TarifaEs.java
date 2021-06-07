package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.*;


@Entity(name = "tarifa_es")
@Data
public class TarifaEs {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String descripcion;
	
}
