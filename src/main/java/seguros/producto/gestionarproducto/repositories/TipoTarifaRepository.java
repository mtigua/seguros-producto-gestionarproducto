package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoTarifa;

@Repository
public interface TipoTarifaRepository extends JpaRepository<TipoTarifa, Long>, TipoTarifaRepositoryCustom {

	
}



