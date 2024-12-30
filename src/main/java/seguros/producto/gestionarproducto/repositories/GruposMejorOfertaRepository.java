package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.CoberturaProductoKey;
import seguros.producto.gestionarproducto.entities.GruposMejorOferta;

@Repository
public interface GruposMejorOfertaRepository extends JpaRepository<GruposMejorOferta, CoberturaProductoKey>, CoberturaRepositoryCustom {
    
}



