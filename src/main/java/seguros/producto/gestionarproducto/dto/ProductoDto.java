package seguros.producto.gestionarproducto.dto;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import lombok.Data;

@Data
public class ProductoDto {
	
	
	private Long id;
	
	@NotNull(message =  "El campo idCiaNegocioRamo es requerido")
	private Integer idCiaNegocioRamo;
	
	@Size(min=4, max=4)
	private String nemot;
	
	@Size(max=30)
	private String descrip;
	
	private Integer equivalenciaSeguros;
	
	@Size(max=4)
	private String domiMoneCod;
	
	private BigDecimal descuentoMax;
	
	@Size(max=14)
	private String docuNroPoliza;	
	
	private Character docuDigPoliza;	
	
	private Integer isae_numero;
	
	private Date docuFecTerVigencia;
	
	private Integer diasVigCotizacion;
	
	private Integer diasVencCuota;
	
	private Integer nroMaxCuotas;
	
	private Integer tipoTraspaso;
	
	private Integer persCodigoAgen;
	
	private Integer persCodigoEjec;
	
	private Integer tipoAcreedor;
	
	private Integer persCodigoAcre;
	
	private Integer tipoFacturar;
	
	private Integer persCodigoFact;
	
	//@Digits(integer=2, fraction=3)
//	@DecimalMax("99.999")
//	@Column(precision = 5, scale = 3, name = "porc_interes")
	private BigDecimal   porcInteres;
	
	private BigDecimal  primaMinima;
	
//	@Column(scale = 2, name = "capital_minimo")
	private BigDecimal  capitalMinimo;
	
//	@Column(scale = 2, name = "capital_maximo")
	private BigDecimal  capitalMaximo;
	
	private String  descripcionPlan;
	
	private Boolean  requiereFamiliar;	
	
	@Range(min=0,max=999999)
	private Integer  diasVigSeguro;
	
	private BigDecimal  factorFijo;	

	@Size(max=20)
	private String  subtipo;	
	
	@Size(max=20)
	private String  producto;
	
	private Integer  hijoMaxedad;
	
	private Boolean  aprobacion;
	
	private Date docuFecIniVigencia;
	
	private Integer  requiereBeneficiarios;
	
	private Integer  antiguedadVehiculo;
	
	private Boolean  multitarifa;
	
	private Boolean  referente;
	
//	@Column(precision = 6, scale = 2, name = "referido_desc")
	private BigDecimal  referidoDesc;
	
//	@Column(precision = 8, scale = 4, name = "referido_prima_minima")
	private BigDecimal  referidoPrimaMinima;
	
	private Boolean  buscaAdicional;
	
	private Integer  clasificacionPlan;
	
	private Boolean  tienePromotor;
	
	private Integer  garantiaSatisfaccion;
	
	private Integer  diasVigenGarantiaSatis;
	
	@Size(max=14)
	private String  nroPolizaMinimo;
	
	@Size(max=14)
	private String  nroPolizaMaximo;
	
	@Size(max=14)
	private String  nroPolizaSiguiente;
	
	private Integer  inspeccion;
	
	private Integer  planPos;
	
	private Boolean  tipoScoring;
	
//	@Column(precision = 8,scale = 4, name="prima_minima_anual")
	private BigDecimal  primaMinimaAnual;
	
	private Boolean excluyePreferente;
	
	private Integer codTCorto;
	
	private String  certificado;
	
	private String  docRenuncia;
	
	private Integer  recalculaEdad;
	
	private BigDecimal  cumuloAporte;
	
	private Integer  cumuloUnico;
	
	private String  docVitrineo;
	
	private Integer  cambioAuto;
	
	private Boolean  regalo;
	
	private Boolean  aplicaTc;
	
	@Max(999999)
	private Integer  tcPermMin;
	
	private Integer  edadMinima;
	
	private Boolean  controlPagoContado;
	
	private Integer grupoCodigo;
		
	private Boolean  usaFactIndiv;
	
	private Date  fechaCierreVtas;
	
	private Boolean  registraVitrineo;
	
	private Integer  dps;
	
	private Boolean  permiteBloqueados;
	
	private Boolean  igualaTarifa;
	
	private Boolean  docuPreimpreso;
	
	private Integer  maxDiasVig;
	
	private Integer  cantCuotasTbk;
	
	private Integer  primeraCuotaTbk;
	
	private Boolean  ofreceMejorAlt;
	
	private Boolean  upgrade;
	
	private Boolean  permVta0;
	
	private Boolean  renCorInme;
	
	@Max(999999)
	private Integer  diasRenCorInme;	
	
	private Boolean  permVtaEjec;
	
	private Integer  mesesPermanencia;
	
	private Integer  mesesCastigo;
	
	private Integer  controlInspeccion;
	
	private Integer  dpsTodos;
	
	private Integer  maxCantAsegurados;
	
	private Boolean  retracto;
	
	private Integer  diasRetracto;
	
//	@Column(precision =4 , scale=4, name = "valor_retracto")
	private BigDecimal  valorRetracto;
	
	@Size(max=5)
	private String  monedaRetracto;
	
	private Boolean  genValiCm;
	
	private Integer  codigoPos;
	
	private Integer ggmCod;
	
	private Integer  usaWs;
	
	private Integer  wsCodigo;
	
	private Integer  usaEmision;
	
	private Integer  wsEmisionCodigo;
	
	private Integer  tarifaNows;
	
	@Size(max=50)
	private String  homologaCiaProd;
	
	@Size(max=50)
	private String  homologaCiaPlan;
	
	@Size(max=50)
	private String  homologaCiaDed;
	
	private Boolean  norenuncia;
	
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

	@NotNull
	private  TipoSeguroDto tipoSeguro;
	
	private  TipoPromocionDto tipoPromocion;	

	private  TipoRecargoDto tipoRecargo;
	
	private  TipoAjusteDto tipoAjuste;
	
	private  TipoDescuentoDto tipoDescuento;
	
	private  TarifaPorDto tarifaPor;
	
	private  TipoTarifaDto tipoTarifa;
	
	private  TipoPeriodoDto tipoPeriodo;
	
	private int idRamo;	
	
	private int idNegocio;
	
	private int idCompania;
	
	private int idGrupoMejorOferta;

}