package seguros.producto.gestionarproducto.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlanUpgradeDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idUpgrade;

    private String nemoVigente;

    private String planVigente;

    private String nemoUpgrade;

    @NotNull(message = "El plan upgrade es requerido")
    private String planUpgrade;

    private String nemoPrevio;

    @NotNull(message = "El plan previo es requerido")
    private String planPrevio;

    @NotNull(message = "Los dias de renuncia son requeridos")
    private Integer diasRenuncia;

}
