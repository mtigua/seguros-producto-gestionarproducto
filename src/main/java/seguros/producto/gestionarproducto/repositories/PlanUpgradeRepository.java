package seguros.producto.gestionarproducto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import seguros.producto.gestionarproducto.entities.PlanUpgrade;

public interface PlanUpgradeRepository extends JpaRepository<PlanUpgrade,Long>, PlanUpgradeRepositoryCustom {
}
