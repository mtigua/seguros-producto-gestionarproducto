package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoTasa;

@Repository
public interface TipoTasaRepository extends JpaRepository<TipoTasa, Long> {

	
}



