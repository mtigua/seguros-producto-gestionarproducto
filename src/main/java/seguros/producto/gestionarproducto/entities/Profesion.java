package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import seguros.producto.gestionarproducto.entities.keys.ProfesionKey;


@Entity(name = "profesion")
@Data
public class Profesion  {

	@EmbeddedId
	private ProfesionKey id;

	
	@Column(name = "porcentaje",scale=2)
	private BigDecimal porcentaje;
	
	
	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumns({
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto",updatable = false, insertable = false),
        @JoinColumn(name = "id_profesion", referencedColumnName = "id_profesion",updatable = false, insertable = false)
    })
	private Set<Criterio> criterios;
	
	public void addCriterio(Criterio criterio) {
        this.criterios.add(criterio);
    }

	public void removeCriterio(Criterio criterio) {
			this.criterios.remove(criterio);
	}

	public void updateCriterio(Criterio criterio) {
		 this.criterios.stream()
				  .filter(t -> criterio.getId().equals( t.getId() ) )
				  .findFirst()
				  .ifPresent(t-> {
					  t=criterio;
				  } );
	}

	

}
