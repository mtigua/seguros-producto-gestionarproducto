package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import javax.persistence.*;


@Entity(name = "catalogo_cantidad_cuotas")
@Data
public class CatalogoCantidadAjuste {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
    
}
