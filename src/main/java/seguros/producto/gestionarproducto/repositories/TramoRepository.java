package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.Tramo;

@Repository
public interface TramoRepository extends JpaRepository<Tramo, Long>, TramoRepositoryCustom {

	
}



