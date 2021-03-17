package seguros.producto.gestionarproducto.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Entity(name = "tipo_compania")
@Data
public class TipoCompania  {
	
	@Id
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String nemot;			
	
    
}
