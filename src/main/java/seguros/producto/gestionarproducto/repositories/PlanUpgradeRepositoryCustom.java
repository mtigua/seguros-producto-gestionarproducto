package seguros.producto.gestionarproducto.repositories;

import seguros.producto.gestionarproducto.dto.PlanUpgradeDto;
import seguros.producto.gestionarproducto.dto.ProdDto;
import seguros.producto.gestionarproducto.servicesImpl.ProductoException;

import java.util.List;

public interface PlanUpgradeRepositoryCustom {
    public List<PlanUpgradeDto> getPlanUpgrade(Long id) throws ProductoException;
    public List<ProdDto> getProductoPorNemotecnico(String nemo) throws ProductoException;
    public List<ProdDto> getPlanesExistentesPorNemotecnico(Long id, String nemoU, String nemoP) throws ProductoException;
    public List<ProdDto> getPlanesAceptadosPorNemotecnico(Long id, String nemoU, String nemoP) throws ProductoException;
    public void deletePlanUpgrade(Long id, Long idUpgrade) throws ProductoException;
}
