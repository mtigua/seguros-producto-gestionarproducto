package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoTramo;

@Repository
public interface TipoTramoRepository extends JpaRepository<TipoTramo, Long>, TipoTramoRepositoryCustom {

	
}



