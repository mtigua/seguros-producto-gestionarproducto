package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.ModoTraspaso;

@Repository
public interface ModoTraspasoRepository extends JpaRepository<ModoTraspaso, Long>, ModoTraspasoRepositoryCustom {

	
}



