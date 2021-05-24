package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoMulta;

@Repository
public interface TipoMultaRepository extends JpaRepository<TipoMulta, Long>, TipoMultaRepositoryCustom {

	
}



