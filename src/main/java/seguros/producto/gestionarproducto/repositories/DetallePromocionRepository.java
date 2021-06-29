package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seguros.producto.gestionarproducto.entities.DetallePromocion;

@Repository
public interface DetallePromocionRepository extends JpaRepository<DetallePromocion, Long>{

	
}



