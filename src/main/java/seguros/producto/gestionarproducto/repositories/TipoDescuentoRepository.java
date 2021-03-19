package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.TipoDescuento;

@Repository
public interface TipoDescuentoRepository extends JpaRepository<TipoDescuento, Long>, TipoDescuentoRepositoryCustom {

	
}



