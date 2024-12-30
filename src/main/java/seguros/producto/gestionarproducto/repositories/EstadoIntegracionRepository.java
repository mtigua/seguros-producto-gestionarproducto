package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.EstadoIntegracion;

@Repository
public interface EstadoIntegracionRepository extends JpaRepository<EstadoIntegracion, Long>, EstadoIntegracionRepositoryCustom {

	
}



