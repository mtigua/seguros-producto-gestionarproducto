package seguros.producto.gestionarproducto.entities;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity(name = "plan_upgrade")
@Data
public class PlanUpgrade {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_vigente")
    private Long planVigente;

    @Column(name = "plan_upgrade",nullable = false)
    private Long planUpgrade;

    @Column(name = "plan_previo",nullable = false)
    private Long planPrevio;

    @Min(0)
    @Column(name = "dias_renuncia",nullable = false)
    private Integer diasRenuncia;

    @Column(name = "fecha_creacion", updatable=false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
}
