package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoSeguro;

@Repository
public interface TipoSeguroRepository extends JpaRepository<TipoSeguro, Long>, TipoSeguroRepositoryCustom {

	
}



