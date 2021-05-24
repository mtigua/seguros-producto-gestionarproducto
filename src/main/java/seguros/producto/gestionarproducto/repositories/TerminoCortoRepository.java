package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TerminoCorto;

@Repository
public interface TerminoCortoRepository extends JpaRepository<TerminoCorto, Long>, TerminoCortoRepositoryCustom {

	
}



