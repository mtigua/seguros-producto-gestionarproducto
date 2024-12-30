package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoCobertura;

@Repository
public interface TipoCoberturaRepository extends JpaRepository<TipoCobertura, Long> {

	
}



