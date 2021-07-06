package seguros.producto.gestionarproducto.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.Producto;
import seguros.producto.gestionarproducto.entities.ProductoDo;
import seguros.producto.gestionarproducto.entities.TerminoCorto;

@Data
public class ProductoDto {


	private Long id;

	@NotNull(message =  "El campo idCiaNegocioRamo es requerido")
	private Integer idCiaNegocioRamo;

	@Size( max=4)
	private String nemot;

	@NotBlank(message =  "El campo nombre es requerido")
	@Size(max=30)
	private String descrip;

	private Integer equivalenciaSeguros;

	@Size(max=4)
	@NotBlank(message = "El campo moneda es requerido")
	private String domiMoneCod;

	private BigDecimal descuentoMax;

	private String docuNroPoliza;

	private String docuDigPoliza;

	private Integer isae_numero;

	private Date docuFecTerVigencia;

	@NotNull(message =  "El campo dias de vigencia cotizacion es requerido")
	private Integer diasVigCotizacion;

	@NotNull(message =  "El campo maximo dias vencimiento 1a cuota es requerido")
	private Integer diasVencCuota;

	@NotNull(message =  "El campo maximo numero de cuotas es requerido")
	private Integer nroMaxCuotas;

	private Long tipoTraspaso;

	private Integer persCodigoAgen;

	private Integer persCodigoEjec;

	private Long tipoAcreedor;

	private String persCodigoAcre;

	private String persDigitoAcre;

	private String persCodigoFactSingle;

	private String persDigitoFactSingle;

	private Long tipoFacturar;


	// vida / vehiculo

	private Integer hijoMaxEdad;
	private Integer edadMinimaIngreso;
	private Boolean conyuge;
	private Boolean otro;
	private Boolean padre;
	private Boolean madre;
	private Boolean hijo;
	private Boolean elMismo;
	private Boolean aseguradoCumulo;
	private Integer mesesCastigo;
	private Boolean declaracionPrincipal;
	private Boolean declaracionAseguradoTodos;
	private String planDpsTextos;
	private String plantillaHtmlDeclaracion1;
	private String plantillaHtmlDeclaracion2;
	private Integer vehiculoHasta;
	private Boolean controlInspeccion;
	private Integer indiqueCodigoWs;
	private Integer indiqueCodigoEmisionWs;




	//@Digits(integer=2, fraction=3)
//	@DecimalMax("99.999")
//	@Column(precision = 5, scale = 3, name = "porc_interes")
	private BigDecimal   porcInteres;

	private BigDecimal  primaMinima;

//	@Column(scale = 2, name = "capital_minimo")
	private BigDecimal  capitalMinimo;

//	@Column(scale = 2, name = "capital_maximo")
	private BigDecimal  capitalMaximo;

	@NotBlank(message =  "El campo descripcion plan es requerido")
	private String  descripcionPlan;

	private Boolean  requiereFamiliar;

	@Range(min=0,max=999999)
	private Integer  diasVigSeguro;

	private BigDecimal  factorFijo;

	@Size(max=20)
	private String  subtipo;

	@Size(max=20)
	private String  producto;

	private Boolean  aprobacion;

	private Date docuFecIniVigencia;

	private Boolean  requiereBeneficiarios;

	private String  antiguedadVehiculo;

	private Boolean  multitarifa;

	private Boolean  referente;

//	@Column(precision = 6, scale = 2, name = "referido_desc")
	private BigDecimal  referidoDesc;

//	@Column(precision = 8, scale = 4, name = "referido_prima_minima")
	private BigDecimal  referidoPrimaMinima;

	private Boolean  buscaAdicional;

	private Integer  clasificacionPlan;

	private Boolean  tienePromotor;

	private Boolean  garantiaSatisfaccion;

	private Integer  diasVigenGarantiaSatis;

	private String  nroPolizaMinimo;

	private String  nroPolizaMaximo;

	@Size(max=14)
	private String  nroPolizaSiguiente;

	private Boolean  inspeccion;

	private Boolean  ventaPos;

	private Boolean  tipoScoring;

	//	@Column(precision = 8,scale = 4, name="prima_minima_anual")
	private BigDecimal  primaMinimaAnual;

	private Boolean excluyePreferente;

	private Integer codTCorto;

	private String  certificado;

	private String  docRenuncia;

	private Boolean  recalculaEdad;

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

	private Boolean  usaWs;

	private Boolean  usaWsEmision;

	private Boolean  tarifaNows;

	@Size(max=50)
	private String idProducto;

	@Size(max=50)
	private String  idPlan;

	@Size(max=50)
	private String  idDed;

	private Boolean  norenuncia;

	private Boolean  validaCm;

	private Boolean perAnual;

	private Boolean perSemestral;

    private Boolean perTrimestral;

	private Boolean perBimensual;

	private Boolean perMensual;

	private Boolean perCuotas;

	@NotNull(message =  "El campo tipo de seguro es requerido")
	private  Long tipoSeguro;

	private  Long tipoPromocion;

	private  Long tipoRecargo;

	private  Long tipoAjuste;

	private  Long tipoDescuento;

	private  Long tarifaPor;

	private  Long tipoTarifa;

	private  Long tipoPeriodo;

	@NotNull(message =  "El campo ramo es requerido")
	private Integer idRamo;

	@NotNull(message =  "El campo negocio es requerido")
	private Integer idNegocio;

	@NotNull(message =  "El campo compania es requerido")
	private Integer idCompania;

	private int idGrupoMejorOferta;

	private FormDataDescripcionOperativaDto productDo;

	@NotBlank(message =  "La contrasena del product manager es requerido")
	private String palabaraPaseProductManager;
	
	private Long[] canales= {4L};

	private Long habilitado=0L;

	private Set<TerminoCorto> terminosCortos;

	
	@JsonIgnore
	public Producto toEntity() {
		Producto p = new Producto();
		BeanUtils.copyProperties(this, p);

		if(this.productDo!=null) {
			ProductoDo pDto= new ProductoDo();
			BeanUtils.copyProperties(this.productDo.toEntity(), pDto);
			p.setProductDo(pDto);
		}

		return p;

	}


}