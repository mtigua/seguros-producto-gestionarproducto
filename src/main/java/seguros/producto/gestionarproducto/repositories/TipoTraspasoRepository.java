package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoTraspaso;

@Repository
public interface TipoTraspasoRepository extends JpaRepository<TipoTraspaso, Long>, TipoTraspasoRepositoryCustom {

	
}



