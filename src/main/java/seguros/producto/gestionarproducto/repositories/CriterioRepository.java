package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seguros.producto.gestionarproducto.entities.Criterio;
import seguros.producto.gestionarproducto.entities.keys.CriterioKey;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, CriterioKey>{

	
}



