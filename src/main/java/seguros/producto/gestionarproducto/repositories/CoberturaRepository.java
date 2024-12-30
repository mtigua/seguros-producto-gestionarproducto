package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.CoberturaProducto;
import seguros.producto.gestionarproducto.entities.CoberturaProductoKey;

@Repository
public interface CoberturaRepository extends JpaRepository<CoberturaProducto, CoberturaProductoKey>, CoberturaRepositoryCustom {

}



