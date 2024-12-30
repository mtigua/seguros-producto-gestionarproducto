package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.Parentesco;

@Repository
public interface ParentescoRepository extends JpaRepository<Parentesco, Long> {

	
}



