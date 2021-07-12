package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.CoberturaProductoKey;
import seguros.producto.gestionarproducto.entities.GruposMejorOferta;

@Repository
public interface GruposMejorOfertaCrudRepository extends CrudRepository<GruposMejorOferta, CoberturaProductoKey>, CoberturaRepositoryCustom {
    Long deleteByProductoId(Long productoId);
}



