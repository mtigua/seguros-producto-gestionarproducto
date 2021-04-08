package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.ProductoDo;

@Repository
public interface ProductoDoRepository extends JpaRepository<ProductoDo, Long>, ProductoDoRepositoryCustom {

//	Page<Producto> findAllByBigsciclo(String ciclo,Pageable pageable);

	
}



