package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.DestinoVenta;

@Repository
public interface DestinoVentaRepository extends JpaRepository<DestinoVenta, Long>, DestinoVentaRepositoryCustom {

	
}



