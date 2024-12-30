package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoAjuste;

@Repository
public interface TipoAjusteRepository extends JpaRepository<TipoAjuste, Long>, TipoAjusteRepositoryCustom {

	
}



