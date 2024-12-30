package seguros.producto.gestionarproducto.entities;

import lombok.Data;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "cobertura_plan")
@Data
public class CoberturaProducto {

	@EmbeddedId
	private CoberturaProductoKey id;


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumns({
            @JoinColumn(name = "id_cobertura", referencedColumnName = "id_cobertura"),
            @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    })
    private Set<TramoCobertura> tramoCoberturas;

    public void addTramoCobertura(TramoCobertura tramoCobertura) {
        this.tramoCoberturas.add(tramoCobertura);
    }

    public void removeTramoCobertura(TramoCobertura tramoCobertura) {
        this.tramoCoberturas.remove(tramoCobertura);
    }

    public void updateCobertura(TramoCobertura tramoCobertura) {
        this.tramoCoberturas.stream()
                .filter(t -> tramoCobertura.getId().equals( t.getId() ) )
                .findFirst()
                .ifPresent(t-> {
                    t=tramoCobertura;
                } );
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_producto")
    @JoinColumn(name = "id_producto", nullable = false)
    private  Producto producto;

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

    @Column(name = "valor_prima")
    private BigDecimal valorPrima;

    @Column(length = 20,name = "monto_asegurado")
    private String montoAsegurado;

    @Column(name = "prima_minima")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prima_sobre_que")
    private PrimaSobreQue primaSobreQue;
 
	
}
