package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.Concepto;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, Long>, ConceptoRepositoryCustom {

	
}



