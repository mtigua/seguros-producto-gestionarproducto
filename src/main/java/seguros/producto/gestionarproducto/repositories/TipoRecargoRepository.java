package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoRecargo;

@Repository
public interface TipoRecargoRepository extends JpaRepository<TipoRecargo, Long>, TipoRecargoRepositoryCustom {

	
}



