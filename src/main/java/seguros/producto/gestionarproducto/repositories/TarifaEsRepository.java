package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TarifaEs;

@Repository
public interface TarifaEsRepository extends JpaRepository<TarifaEs, Long>, TarifaEsRepositoryCustom {

	
}



