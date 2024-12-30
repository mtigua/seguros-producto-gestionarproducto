package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.HomologacionIdentificador;

@Repository
public interface HomologacionIdentificadorRepository extends JpaRepository<HomologacionIdentificador, Long>, HomologacionIdentificadorRepositoryCustom {

	
}



