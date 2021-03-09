package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;

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
	
	@Column(length = 30)
	private String descrip;
	
	@Column(name = "equivalencia_seguros")
	private Integer equivalenciaSeguros;
	
	@Column(length = 4, name = "domi_mone_cod")
	private String domiMoneCod;
	
	@Column( name = "descuento_max")
	private BigDecimal descuentoMax;
	
	@Column(length = 14, name = "docu_nro_poliza")
	private String docuNroPoliza;	
	
	@Column(name = "docu_dig_poliza")
	private Character docuDigPoliza;	
	
	private Integer isae_numero;
	
	@Column(name = "docu_fec_ter_vigencia")
	private Date docuFecTerVigencia;
	
	@Column(name = "dias_vig_cotizacion")
	private Integer diasVigCotizacion;
	
	@Column(name = "dias_venc_cuota")
	private Integer diasVencCuota;
	
	@Column(name = "nro_max_cuotas")
	private Integer nroMaxCuotas;
	
	@Column(name = "tipo_traspaso")
	private Integer tipoTraspaso;
	
	@Column(name = "pers_codigo_agen")
	private Integer persCodigoAgen;
	
	@Column(name = "pers_codigo_ejec")
	private Integer persCodigoEjec;
	
	@Column(name = "tipo_acreedor")
	private Integer tipoAcreedor;
	
	@Column(name = "pers_codigo_acre")
	private Integer persCodigoAcre;
	
	@Column(name = "tipo_facturar")
	private Integer tipoFacturar;
	
	@Column(name = "pers_codigo_fact")
	private Integer persCodigoFact;
	
	//@Digits(integer=2, fraction=3)
	@DecimalMax("99.999")
	@Column(precision = 5, scale = 3, name = "porc_interes")
	private BigDecimal   porcInteres;
	
	@Column(name = "prima_minima")
	private BigDecimal  primaMinima;
	
	@Column(scale = 2, name = "capital_minimo")
	private BigDecimal  capitalMinimo;
	
	@Column(scale = 2, name = "capital_maximo")
	private BigDecimal  capitalMaximo;
	
	@Column(name = "descripcion_plan")
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
	private Integer  hijoMaxedad;
	
	private Boolean  aprobacion;
	
	@Column(name = "docu_fec_ini_vigencia")
	private Date docuFecIniVigencia;
	
	@Column(name = "requiere_beneficiarios")
	private Integer  requiereBeneficiarios;
	
	@Column(name = "antiguedad_vehiculo")
	private Integer  antiguedadVehiculo;
	
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
	private Integer  garantiaSatisfaccion;
	
	@Column(name = "dias_vigen_garantia_satis")
	private Integer  diasVigenGarantiaSatis;
	
	@Column(length = 14, name = "nro_poliza_minimo")
	private String  nroPolizaMinimo;
	
	@Column(length = 14, name = "nro_poliza_maximo")
	private String  nroPolizaMaximo;
	
	@Column(length = 14, name = "nro_poliza_siguiente")
	private String  nroPolizaSiguiente;
	
	private Integer  inspeccion;
	
	@Column(name = "plan_pos")
	private Integer  planPos;
	
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
	private Integer  recalculaEdad;
	
	@Column(name = "cumulo_aporte")
	private BigDecimal  cumuloAporte;
	
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
	
	private Integer  dps;
	
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
	private Integer  controlInspeccion;
	
	@Column(name = "dps_todos")
	private Integer  dpsTodos;
	
	@Column(name = "max_cant_asegurados")
	private Integer  maxCantAsegurados;
	
	@Column(name = "retracto")
	private Boolean  retracto;
	
	@Column(name = "dias_retracto")
	private Integer  diasRetracto;
	
	@Column(precision =4 , scale=4, name = "valor_retracto")
	private BigDecimal  valorRetracto;
	
	@Column(length = 5, name = "moneda_retracto")
	private String  monedaRetracto;
	
	@Column(name = "gen_vali_cm")
	private Boolean  genValiCm;
	
	@Column(name = "codigo_pos")
	private Integer  codigoPos;
	
	@Column(name = "ggm_cod")
	private Integer ggmCod;
	
	@Column(name = "usa_ws")
	private Integer  usaWs;
	
	@Column(name = "ws_codigo")
	private Integer  wsCodigo;
	
	@Column(name = "usa_emision")
	private Integer  usaEmision;
	
	@Column(name = "ws_emision_codigo")
	private Integer  wsEmisionCodigo;
	
	@Column(name = "tarifa_nows")
	private Integer  tarifaNows;
	
	@Column(length = 50, name = "homologa_cia_prod")
	private String  homologaCiaProd;
	
	@Column(length = 50, name = "homologa_cia_plan")
	private String  homologaCiaPlan;
	
	@Column(length = 50, name = "homologa_cia_ded")
	private String  homologaCiaDed;
	
	private Boolean  norenuncia;
	
	@Column(name = "valida_cm")
	private Boolean  validaCm;
	
	//New fields
	
     // Desaparece este campo:  PLCO_TARIFA_PERIOD;
	
	private Boolean perAnual;
	
	private Boolean perSemestral;
	
	private Boolean perTrimestral;
	
	private Boolean perBimensual;
	
	private Boolean perMensual;
	
	private Boolean perCuotas;
	
	//Desaparece este campo : PLCO_PARENTESCOS;
	
    private Boolean parenEsConyuge;
	
	private Boolean parenEsMadre;
	
	private Boolean parenEsOtro;
	
	private Boolean parenEsPadre;
	
	private Boolean parenElMismo;
	
	private Boolean parenEsHijo;
	

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
	
	@Column(name = "id_ramo")
	private int idRamo;	
	
	@Column(name = "id_negocio")
	private int idNegocio;
	
	@Column(name = "id_compania")
	private int idCompania;
	
	@Column(name = "id_grupo_mejor_oferta")
	private int idGrupoMejorOferta;
    
}
