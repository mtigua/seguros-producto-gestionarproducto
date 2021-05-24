package seguros.producto.gestionarproducto.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "cobertura_plan")
@Data
public class CoberturaProducto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private  Producto producto;

    @Column(name = "id_cobertura")
    private int idCobertura;

    @Column(name = "id_deducible")
    private int idDeducible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private TipoCobertura tipoCobertura;

    @Column
    private BigDecimal tasa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expresada_en")
    private TipoTasa tipoTasa;

    @Column(length = 50)
    private String iva;

    @Column(precision = 8, scale = 4,name = "valor_prima")
    private BigDecimal valorPrima;

    @Column(length = 20,name = "monto_asegurado")
    private String montoAsegurado;

    @Column(precision = 8, scale = 4,name = "prima_minima")
    private BigDecimal primaMinima;

    @Column(name = "edad_max_ingreso")
    private Integer edadMaxIngreso;

    @Column(name = "edad_max_permanencia")
    private Integer edadMaxPermanencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parentesco")
    private Parentesco parentesco;

    @Column(name = "porc_capital")
    private BigDecimal porcCapital;

    @Column
    private Integer orden;

    @Column(name = "porc_prima")
    private BigDecimal porcPrima;

    @Column(name = "id_prima_sobre_que")
    @ManyToOne(fetch = FetchType.LAZY)
    private PrimaSobreQue primaSobreQue;

}
