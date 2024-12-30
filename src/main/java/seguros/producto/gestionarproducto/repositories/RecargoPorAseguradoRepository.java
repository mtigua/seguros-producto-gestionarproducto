package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.RecargoPorAsegurado;

@Repository
public interface RecargoPorAseguradoRepository extends JpaRepository<RecargoPorAsegurado, Long>, RecargoPorAseguradoRepositoryCustom {

}
