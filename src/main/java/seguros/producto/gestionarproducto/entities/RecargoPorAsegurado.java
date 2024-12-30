package seguros.producto.gestionarproducto.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity(name = "recargo_por_asegurado")
@Data
public class RecargoPorAsegurado {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Column(name = "desde_asegurado",nullable = false)
    private Integer desdeAsegurado;

    @Min(1)
    @Column(name = "hasta_asegurado",nullable = false)
    private Integer hastaAsegurado;

    @Column(precision =6 , scale=4, nullable = false)
    private BigDecimal porcentajeRecargo;

    @Column(name = "fecha_creacion", updatable=false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
}
