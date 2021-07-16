package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seguros.producto.gestionarproducto.entities.Producto;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>, ProductoRepositoryCustom {

//	Page<Producto> findAllByBigsciclo(String ciclo,Pageable pageable);

	
}



