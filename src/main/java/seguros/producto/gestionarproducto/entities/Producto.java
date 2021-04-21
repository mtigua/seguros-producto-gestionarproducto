package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Entity(name = "plan_de_cobertura")
@Data
public class Producto  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "id_cia_negocio_ramo")
	private Integer idCiaNegocioRamo;
	
	@Column(length = 4)
	private String nemot;
	
	@Column(nullable = false, length = 30)
	private String descrip;
	
	@Column(name = "equivalencia_seguros")
	private Integer equivalenciaSeguros;
	
	@Column(nullable = false,length = 4, name = "domi_mone_cod")
	private String domiMoneCod;
	
	@Column( name = "descuento_max")
	private BigDecimal descuentoMax;
	
	@Column(length = 14, name = "docu_nro_poliza")
	private String docuNroPoliza;	
	
	@Column(name = "docu_dig_poliza")
	private Character docuDigPoliza;
	
	@Column(name = "isae_numero")
	private Integer isaeNumero;
	
	@Column(name = "docu_fec_ter_vigencia")
	private Date docuFecTerVigencia;
	
	@Column(nullable = false, name = "dias_vig_cotizacion")
	private Integer diasVigCotizacion;
	
	@Column(nullable = false,name = "dias_venc_cuota")
	private Integer diasVencCuota;
	
	@Column(nullable = false,name = "nro_max_cuotas")
	private Integer nroMaxCuotas;
		
	@Column(name = "pers_codigo_agen")
	private Integer persCodigoAgen;
	
	@Column(name = "pers_codigo_ejec")
	private Integer persCodigoEjec;
	
	@Column(name = "pers_codigo_acre")
	private Integer persCodigoAcre;

	@Column(name = "conyuge")
	private Boolean conyuge;

	@Column(name = "otro")
	private Boolean otro;

	@Column(name = "padre")
	private Boolean padre;

	@Column(name = "madre")
	private Boolean madre;

	@Column(name = "hijo")
	private Boolean hijo;

	@Column(name = "el_mismo")
	private Boolean elMismo;

	@Column(name = "asegurado_cumulo")
	private Boolean aseguradoCumulo;

	@Column(name = "declaracion_principal")
	private Boolean declaracionPrincipal;

	@Column(name = "cumulo_aporte")
	private Long cumuloAporte;
	
	@Column(name = "pers_codigo_fact_individual")
	private String persCodigoFact;

	@Column(name = "pers_digito_acre_individual")
	private String persDigitoFact;

	@Column(name = "pers_codigo_fact")
	private String persCodigoFactSingle;

	@Column(name = "pers_digito_acre")
	private String persDigitoFactSingle;
	
	@DecimalMax("99.999")
	@Column(precision = 5, scale = 3, name = "porc_interes")
	private BigDecimal   porcInteres;
	
	@Column(name = "prima_minima")
	private BigDecimal  primaMinima;
	
	@Column(scale = 2, name = "capital_minimo")
	private BigDecimal  capitalMinimo;
	
	@Column(scale = 2, name = "capital_maximo")
	private BigDecimal  capitalMaximo;
	
	@Column(nullable = false, name = "descripcion_plan")
	private String  descripcionPlan;
	
	@Column(name = "requiere_familiar")
	private Boolean  requiereFamiliar;
	
	@Max(999999)
	@Column(name = "dias_vig_seguro")
	private Integer  diasVigSeguro;
	
	@Column(name = "factor_fijo")
	private BigDecimal  factorFijo;
	
	@Column(length = 20)
	private String  subtipo;	
	
	@Column(length = 20)
	private String  producto;
	
	@Column(name = "hijo_maxedad")
	private Integer  hijoMaxEdad;

	@Column(name = "edad_minimo_ingreso")
	private Integer  edadMinimaIngreso;
	
	private Boolean  aprobacion;
	
	@Column(name = "docu_fec_ini_vigencia")
	private Date docuFecIniVigencia;
	
	@Column(name = "requiere_beneficiarios")
	private Boolean  requiereBeneficiarios;
	
	@Column(name = "antiguedad_vehiculo")
	private String  antiguedadVehiculo;
	
	private Boolean  multitarifa;
	
	private Boolean  referente;
	
	@Column(precision = 6, scale = 2, name = "referido_desc")
	private BigDecimal  referidoDesc;
	
	@Column(precision = 8, scale = 4, name = "referido_prima_minima")
	private BigDecimal  referidoPrimaMinima;
	
	@Column(name = "busca_adicional")
	private Boolean  buscaAdicional;
	
	@Column(name = "clasificacion_plan")
	private Integer  clasificacionPlan;
	
	@Column(name = "tiene_promotor")
	private Boolean  tienePromotor;
	
	@Column(name = "garantia_satisfaccion")
	private Boolean  garantiaSatisfaccion;
	
	@Column(name = "dias_vigen_garantia_satis")
	private Integer  diasVigenGarantiaSatis;
	
	@Column(length = 14, name = "nro_poliza_minimo")
	private String  nroPolizaMinimo;
	
	@Column(length = 14, name = "nro_poliza_maximo")
	private String  nroPolizaMaximo;
	
	@Column(length = 14, name = "nro_poliza_siguiente")
	private String  nroPolizaSiguiente;
	
	private Boolean  inspeccion;
	
	@Column(name = "venta_pos")
	private Boolean  ventaPos;
	
	@Column(name = "tipo_scoring")
	private Boolean  tipoScoring;
	
	@Column(precision = 8,scale = 4, name="prima_minima_anual")
	private BigDecimal  primaMinimaAnual;
	
	@Column(name = "excluye_preferente")
	private Boolean excluyePreferente;
	
	@Column(name = "cod_t_corto")
	private Integer codTCorto;
	
	private String  certificado;
	
	@Column(name = "doc_renuncia")
	private String  docRenuncia;
	
	@Column(name = "recalcula_edad")
	private Boolean  recalculaEdad;
	
	@Column(name = "cumulo_unico")
	private Integer  cumuloUnico;
	
	@Column(name = "doc_vitrineo")
	private String  docVitrineo;
	
	@Column(name = "cambio_auto")
	private Integer  cambioAuto;
	
	private Boolean  regalo;
	
	@Column(name = "aplica_tc")
	private Boolean  aplicaTc;
	
	@Max(999999)
	@Column(name = "tc_perm_min")
	private Integer  tcPermMin;
	
	@Column(name = "edad_minima")
	private Integer  edadMinima;
	
	@Column(name = "control_pago_contado")
	private Boolean  controlPagoContado;
	
	@Column(name = "grupo_codigo")
	private Integer grupoCodigo;
		
	@Column(name = "usa_fact_indiv")
	private Boolean  usaFactIndiv;
	
	@Column(name = "fecha_cierre_vtas")
	private Date  fechaCierreVtas;
	
	@Column(name = "registra_vitrineo")
	private Boolean  registraVitrineo;
	
	@Column(name = "permite_bloqueados")
	private Boolean  permiteBloqueados;
	
	@Column(name = "iguala_tarifa")
	private Boolean  igualaTarifa;
	
	@Column(name = "docu_preimpreso")
	private Boolean  docuPreimpreso;
	
	@Column(name = "max_dias_vig")
	private Integer  maxDiasVig;
	
	@Column(name = "cant_cuotas_tbk")
	private Integer  cantCuotasTbk;
	
	@Column(name = "primera_cuota_tbk")
	private Integer  primeraCuotaTbk;
	
	@Column(name = "ofrece_mejor_alt")
	private Boolean  ofreceMejorAlt;
	
	private Boolean  upgrade;
	
	@Column(name = "perm_vta0")
	private Boolean  permVta0;
	
	@Column(name = "ren_cor_inme")
	private Boolean  renCorInme;
	
	@Max(999999)
	@Column(name = "dias_ren_cor_inme")
	private Integer  diasRenCorInme;	
	
	@Column(name = "perm_vta_ejec")
	private Boolean  permVtaEjec;
	
	@Column(name = "meses_permanencia")
	private Integer  mesesPermanencia;
	
	@Column(name = "meses_castigo")
	private Integer  mesesCastigo;
	
	@Column(name = "control_inspeccion")
	private Boolean  controlInspeccion;

	@Column(name = "declaracion_asegurado")
	private Boolean  declaracionAseguradoTodos;
	
	@Column(name = "max_cant_asegurados")
	private Integer  maxCantAsegurados;
	
	@Column(name = "retracto")
	private Boolean  retracto;
	
	@Column(name = "dias_retracto")
	private Integer  diasRetracto;

	@Column(name = "indique_codigo_emision_ws")
	private Integer  indiqueCodigoEmisionWs;

	@Column(name = "indique_codigo_ws")
	private Integer  indiqueCodigoWs;

	@Column(name = "vehiculo_hasta")
	private Integer  vehiculoHasta;
	
	@Column(precision =4 , scale=4, name = "valor_retracto")
	private BigDecimal  valorRetracto;
	
	@Column(length = 5, name = "moneda_retracto")
	private String  monedaRetracto;

	@Column(length = 200, name = "plantilla_html_declaracion1")
	private String  plantillaHtmlDeclaracion1;

	@Column(length = 200, name = "plantilla_html_declaracion2")
	private String  plantillaHtmlDeclaracion2;

	@Column(length = 200, name = "plan_dps_textos")
	private String  planDpsTextos;
	
	@Column(name = "gen_vali_cm")
	private Boolean  genValiCm;
	
	@Column(name = "codigo_pos")
	private Integer  codigoPos;
	
	@Column(name = "ggm_cod")
	private Integer ggmCod;
	
	@Column(name = "usa_ws")
	private Boolean  usaWs;
	
	@Column(name = "usa_ws_emision")
	private Boolean  usaWsEmision;

	@Column(name = "tarifa_nows")
	private Boolean  tarifaNows;
	
	@Column(length = 50, name = "homologa_cia_prod")
	private String  homologaCiaProd;
	
	@Column(length = 50, name = "id_plan")
	private String  idPlan;
	
	@Column(length = 50, name = "id_ded")
	private String  idDed;
	
	private Boolean  norenuncia;
	
	@Column(name = "valida_cm")
	private Boolean  validaCm;	
	
	private Boolean perAnual;
	
	private Boolean perSemestral;
	
	private Boolean perTrimestral;
	
	private Boolean perBimensual;
	
	private Boolean perMensual;
	
	private Boolean perCuotas;

	@ManyToOne
	@JoinColumn(name = "id_tipo_seguro", nullable=false)
	private  TipoSeguro tipoSeguro;
	
	@ManyToOne
	@JoinColumn(name = "id_promocion_adjunta")
	private  TipoPromocion tipoPromocion;	

	@ManyToOne
	@JoinColumn(name = "id_aplica_recargo")
	private  TipoRecargo tipoRecargo;
	
	@ManyToOne
	@JoinColumn(name = "id_ajuste_es")
	private  TipoAjuste tipoAjuste;
	
	@ManyToOne
	@JoinColumn(name = "id_aplica_descuento")
	private  TipoDescuento tipoDescuento;
	
	@ManyToOne
	@JoinColumn(name = "id_tarifaPor")
	private  TarifaPor tarifaPor;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_tarifa")
	private  TipoTarifa tipoTarifa;
	
	@ManyToOne
	@JoinColumn(name = "id_tarifa_es")
	private  TipoPeriodo tipoPeriodo;
	
	@Column(nullable = false,name = "id_ramo")
	private int idRamo;	
	
	@Column(nullable = false,name = "id_negocio")
	private int idNegocio;
	
	@Column(nullable = false,name = "id_compania")
	private int idCompania;
	
	@Column(name = "id_grupo_mejor_oferta")
	private int idGrupoMejorOferta;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_traspaso")
	private  TipoTraspaso tipoTraspaso;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_acreedor")
	private  ModoTraspaso tipoAcreedor;

	@ManyToOne
	@JoinColumn(name = "id_tipo_facturar")
	private  ModoTraspaso tipoFacturar;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true)
	@JoinColumn(name = "id_plan_de_cobertura_do")
	private ProductoDo productDo;
	
	@Lob
	@Column(nullable = false,name = "palabra_pase_product_manager")
	private String palabaraPaseProductManager;	
    
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
	 
	
		
		
		
	
}
