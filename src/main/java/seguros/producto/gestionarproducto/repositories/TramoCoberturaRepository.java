package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TramoCobertura;

@Repository
public interface TramoCoberturaRepository extends JpaRepository<TramoCobertura, Long>, TramoCoberturaRepositoryCustom {

	
}



