package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.DatoComplementario;

@Repository
public interface DatoComplementarioRepository extends JpaRepository<DatoComplementario, Long>, DatoComplementarioRepositoryCustom {

	
}



