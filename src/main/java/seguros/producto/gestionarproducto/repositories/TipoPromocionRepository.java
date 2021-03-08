package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoPromocion;

@Repository
public interface TipoPromocionRepository extends JpaRepository<TipoPromocion, Long>, TipoPromocionRepositoryCustom {

	
}



