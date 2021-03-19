package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.Canal;

@Repository
public interface CanalRepository extends JpaRepository<Canal, Long>, CanalRepositoryCustom {

	
}



