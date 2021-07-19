package seguros.producto.gestionarproducto.entities;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;
import seguros.producto.gestionarproducto.entities.keys.CriterioKey;


@Entity(name = "criterio")
@Data
public class Criterio  {
	
	@EmbeddedId
	private CriterioKey id;

    
}
