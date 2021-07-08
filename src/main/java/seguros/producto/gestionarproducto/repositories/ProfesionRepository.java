package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.DetallePromocion;
import seguros.producto.gestionarproducto.entities.Profesion;
import seguros.producto.gestionarproducto.entities.ProfesionKey;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, ProfesionKey>{

	
}



