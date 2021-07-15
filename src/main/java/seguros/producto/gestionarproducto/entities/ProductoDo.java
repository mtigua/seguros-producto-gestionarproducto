package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Entity(name = "plan_de_cobertura_do")
@Data
public class ProductoDo  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "do_responsable")
	private Integer doResponsable;
	
	@Column(name = "dopl_masivo")
	private Boolean doplMasivo;
	
	@Column(name = "dopl_fec_ini_vta")
	private Date doplFecIniVta;
	
	@Column(name = "dopl_pol_svs")
	private Integer doplPolSvs;
	
	@Column( name = "dopl_porc_comi_sp")
	private BigDecimal doplPorcComiSp;
	
	@Column(name = "dopl_porc_comi_adm")
	private BigDecimal doplPorcComiAdm;	
	
	@Column(name = "dopl_valor_comi_adm")
	private BigDecimal doplValorComiAdm;	
	
	@Column(name = "dopl_porc_afecto")
	private BigDecimal doplPorcAfecto;
	
	@Column(name = "dopl_ctocob_mora1")
	private BigDecimal doplCtocobMora1;
	
	@Column(name = "dopl_tope_mora1")
	private BigDecimal doplTopeMora1;
	
	@Column(name = "dopl_ctocob_mora2")
	private BigDecimal doplCtocobMora2;
	
	@Column(name = "dopl_tope_mora2")
	private BigDecimal doplTopeMora2;
		
	@Column(name = "dopl_porc_comi_cat")
	private BigDecimal doplPorcComiCat;
	
	@Column(name = "dopl_vta_mes")
	private Integer doplVtaMes;
	
	@Column(name = "dopl_usa_cheque_rest")
	private Boolean doplUsaChequeRest;
	
	@Column(name = "dopl_aplica_suspension")
	private Boolean doplAplicaSuspension;
	
	@Column(name = "dopl_carta_mora")
	private Boolean   doplCartaMora;
	
	@Column(name = "dopl_cob_proporcional")
	private Boolean  doplCobProporcional;
	
	@Column(name = "dopl_envio_bienvenida")
	private Boolean  doplEnvioBienvenida;
	
	@Column(name = "dopl_novalida_tit_cerrada")
	private Boolean  doplNovalidaTitCerrada;
	
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@Column(name = "file_name_condicionado_particular")
	private String fileNameCondicionadoParticular;
	
	@ManyToOne
	@JoinColumn(name = "id_destino")
	private  DestinoVenta doplAQuienSeVende;
	
	
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
	
    
	
}
