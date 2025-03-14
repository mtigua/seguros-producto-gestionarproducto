package seguros.producto.gestionarproducto.entities;



import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column( name = "id_cia_negocio_ramo")
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
	
	@Column(length = 2, name = "docu_dig_poliza")
	private String docuDigPoliza;
	
	@Column(name = "isae_numero")
	private Integer isaeNumero;
	
	@Column(name = "docu_fec_ter_vigencia")
	private Date docuFecTerVigencia;
	
	@Column( name = "dias_vig_cotizacion")
	private Integer diasVigCotizacion=7;
	
	@Column(name = "dias_venc_cuota")
	private Integer diasVencCuota=30;
	
	@Column(name = "nro_max_cuotas")
	private Integer nroMaxCuotas=12;
		
	@Column(name = "pers_codigo_agen")
	private Integer persCodigoAgen;
	
	@Column(name = "pers_codigo_ejec")
	private Integer persCodigoEjec;

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
	private BigDecimal cumuloAporte;

	// acreedores
	@Column(name = "pers_digito_acre")
	private String persDigitoAcre;

	@Column(name = "pers_codigo_acre")
	private String persCodigoAcre;

	// facturas
	@Column(name = "pers_codigo_fact")
	private String persCodigoFactSingle;

	@Column(name = "pers_digito_fact")
	private String persDigitoFactSingle;

	
	@DecimalMax("99.999")
	@Column(precision = 5, scale = 3, name = "porc_interes")
	private BigDecimal   porcInteres;
	
	@Column( precision = 8, scale = 4,name = "prima_minima")
	private BigDecimal  primaMinima;
	
	@Column(scale = 2, name = "capital_minimo")
	private BigDecimal  capitalMinimo;
	
	@Column(scale = 2, name = "capital_maximo")
	private BigDecimal  capitalMaximo;
	
	@Column( name = "descripcion_plan")
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
	private Boolean  retracto=Boolean.TRUE;
	
	@Column(name = "dias_retracto")
	private Integer  diasRetracto=35;

	@Column(name = "indique_codigo_emision_ws")
	private Integer  indiqueCodigoEmisionWs;

	@Column(name = "indique_codigo_ws")
	private Integer  indiqueCodigoWs;

	@Column(name = "vehiculo_hasta")
	private Integer  vehiculoHasta;
	
	@Column(precision =8 , scale=4, name = "valor_retracto")
	private BigDecimal  valorRetracto=new BigDecimal(0.0);
	
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
	
	@Column(length = 50, name = "id_producto")
	private String idProducto;
	
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
	
	private Long habilitado = 0L;

	@ManyToOne
	@JoinColumn(name = "id_tipo_seguro")
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
	private Integer idRamo;	
	
	@Column(name = "id_negocio")
	private Integer idNegocio;
	
	@Column(name = "id_compania")
	private Integer idCompania;
	
	@Column(name = "id_grupo_mejor_oferta")
	private int idGrupoMejorOferta;
	
	@Column(name = "file_name_condicionado_particular")
	private String fileNameCondicionadoParticular;
	
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
	@Column(name = "palabra_pase_product_manager")
	private String palabaraPaseProductManager;	
	
	@ManyToMany( cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
		})
		@JoinTable( name = "producto_canal",
			joinColumns = @JoinColumn (name = "producto_id"),
			inverseJoinColumns =  @JoinColumn(name = "canal_id")
		)
	private Set<Canal> canales = new HashSet<Canal>();
		
	public void addCanal(Canal canal) {
	        this.canales.add(canal);
	 }
		
	public void removeCanal(Canal canal) {
			this.canales.remove(canal);
	}
    
	
	@Column(name = "fecha_creacion", updatable=false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;

	
	
	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "producto_id")
	private Set<TerminoCorto> terminosCortos;
	
	public void addTerminoCorto(TerminoCorto terminoCorto) {
        this.terminosCortos.add(terminoCorto);
    }
	
	public void removeTerminoCorto(TerminoCorto terminoCorto) {
			this.terminosCortos.remove(terminoCorto);
	}
	
	public void updateTerminoCorto(TerminoCorto terminoCorto) {
		 this.terminosCortos.stream()
				  .filter(t -> terminoCorto.getId().equals( t.getId() ) )
				  .findFirst()
				  .ifPresent(t-> {
					  t=terminoCorto;
					  
				  } );
	
	}
	
	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "producto_id")
	private Set<Tramo> tramos;
	
	public void addTramo(Tramo tramo) {
        this.tramos.add(tramo);
    }
	
	public void removeTramo(Tramo tramo) {
			this.tramos.remove(tramo);
	}
	
	public void updateTramo(Tramo tramo) {
		 this.tramos.stream()
				  .filter(t -> tramo.getId().equals( t.getId() ) )
				  .findFirst()
				  .ifPresent(t-> {
					  t=tramo;
					  
				  } );
	
	}


	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "producto_id")
	private Set<RecargoPorAsegurado> recargoPorAsegurado;

	public void addRecargoPorAsegurado(RecargoPorAsegurado recargoPorAsegurado) {
		this.recargoPorAsegurado.add(recargoPorAsegurado);
	}

	public void removeRecargoPorAsegurado(RecargoPorAsegurado recargoPorAsegurado) {
		this.recargoPorAsegurado.remove(recargoPorAsegurado);
	}

	public void updateRecargoPorAsegurado(RecargoPorAsegurado recargoPorAsegurado) {
		this.recargoPorAsegurado.stream()
				.filter(t -> recargoPorAsegurado.getId().equals( t.getId() ) )
				.findFirst()
				.ifPresent(t-> {
					t=recargoPorAsegurado;

				} );

	}


	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "producto_id")
	private Set<DetallePromocion> detallePromociones;

	public void addDetallePromocion(DetallePromocion detallePromocion) {
        this.detallePromociones.add(detallePromocion);
    }

	public void removeDetallePromocion(DetallePromocion detallePromocion) {
			this.detallePromociones.remove(detallePromocion);
	}

	public void updateDetallePromocion(DetallePromocion detallePromocion) {
		 this.detallePromociones.stream()
				  .filter(t -> detallePromocion.getId().equals( t.getId() ) )
				  .findFirst()
				  .ifPresent(t-> {
					  t=detallePromocion;
				  } );
	}

	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "plan_vigente")
	private Set<PlanUpgrade> planUpgrade;

	public void addPlanUpgrade(PlanUpgrade planUpgrade) {
		this.planUpgrade.add(planUpgrade);
	}

	public void removePlanUpgrade(PlanUpgrade planUpgrade) {
		this.planUpgrade.remove(planUpgrade);
	}

	public void updatePlanUpgrade(PlanUpgrade planUpgrade) {
		this.planUpgrade.stream()
				.filter(t -> planUpgrade.getId().equals( t.getId() ) )
				.findFirst()
				.ifPresent(t-> {
					t=planUpgrade;

				} );
	}

	@OneToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "id_producto", updatable = false, insertable = false)
	private Set<Profesion> profesiones;
	
	public void addProfesion(Profesion profesion) {
        this.profesiones.add(profesion);
    }

	public void removeProfesion(Profesion profesion) {
			this.profesiones.remove(profesion);
	}

	public void updateProfesion(Profesion profesion) {
		 this.profesiones.stream()
				  .filter(t -> profesion.getId().equals( t.getId() ) )
				  .findFirst()
				  .ifPresent(t-> {
					  t=profesion;
				  } );
	}

	
	
}
