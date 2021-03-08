package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TarifaPor;

@Repository
public interface TarifaPorRepository extends JpaRepository<TarifaPor, Long>, TarifaPorRepositoryCustom {

	
}



