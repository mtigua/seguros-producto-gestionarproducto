package seguros.producto.gestionarproducto.dto;

import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.RecargoPorAsegurado;

@Data
public class RecargoPorAseguradoDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad desde Nro Asegurado es requerido")
    private Integer desdeAsegurado;

    @NotNull(message = "La cantidad hasta Nro Asegurado es requerido")
    private Integer hastaAsegurado;

    @NotNull(message = "El porcentaje de recargo es requerido")
    private BigDecimal porcentajeRecargo;

    @JsonIgnore
    public RecargoPorAsegurado toEntity() {
        RecargoPorAsegurado p = new RecargoPorAsegurado();
        BeanUtils.copyProperties(this, p);

        return p;

    }
}
