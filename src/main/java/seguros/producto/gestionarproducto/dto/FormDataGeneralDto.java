package seguros.producto.gestionarproducto.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import lombok.Data;



@Data
public class FormDataGeneralDto {


	private Long id;

	private Boolean  aprobacion;
	
	private Boolean  buscaAdicional;
	
	private Boolean  referente;
	
	private Boolean  tienePromotor;
	
	private Boolean  tipoScoring;
	
	private Boolean  multitarifa;
	
	private Boolean  regalo;
	
	private BigDecimal  referidoDesc;
	
	private Boolean  controlPagoContado;	
	
	private Boolean  usaFactIndiv;
	
	private Boolean  upgrade;
	
	private Boolean  registraVitrineo;
	
	private BigDecimal  referidoPrimaMinima;	
	
	private Boolean  permiteBloqueados;
	
	private Boolean  igualaTarifa;
	
	private Boolean  docuPreimpreso;
	
	private Boolean  ofreceMejorAlt;
	
	private Integer  maxDiasVig;	
	
	private Boolean  permVta0;
	
	private  Long tipoPromocion;
	
	private Boolean  permVtaEjec;
	
	private BigDecimal   porcInteres;
	
	@NotNull(message =  "El campo maximo numero de cuotas es requerido")
	private Integer nroMaxCuotas;
	
	@Range(min=0,max=999999)
	private Integer  diasVigSeguro;
	
	private BigDecimal  primaMinimaAnual;
	
	@NotNull(message =  "El campo maximo dias vencimiento 1a cuota es requerido")
	private Integer diasVencCuota;
	
	@NotNull(message =  "El campo dias de vigencia cotizacion es requerido")
	private Integer diasVigCotizacion;
	
	private  Long tipoAjuste;
	
	private Boolean excluyePreferente;
	
	private Boolean  genValiCm;

	private Boolean  norenuncia;
	
	private Boolean  validaCm;
	
	private Boolean  renCorInme;
	
	@Max(999999)
	private Integer  diasRenCorInme;	
	
	private BigDecimal  primaMinima;
	
	private Boolean  retracto;
	
	private Integer  diasRetracto;
	
	private BigDecimal  valorRetracto;
	
	@Size(max=5)
	private String  monedaRetracto;
	
	private BigDecimal descuentoMax;
	
	private String  certificado;
	
	private BigDecimal  capitalMinimo;
	
	private String  docRenuncia;
	
	private BigDecimal  capitalMaximo;
	
	private String  docVitrineo;
	
	private  Long tipoRecargo;
	
	private  Long tipoDescuento;
	
	private  Long tarifaPor;
	
	private  Long tipoTarifa;
	
	private Boolean perCuotas;
	
	private Boolean perAnual;

	private Boolean perSemestral;

    private Boolean perTrimestral;

	private Boolean perBimensual;

	private Boolean perMensual;
	
	private  Long tipoPeriodo;
	
	private Boolean  garantiaSatisfaccion;
	
	private Integer  diasVigenGarantiaSatis;
	
	private Boolean  aplicaTc;
	
	@Max(999999)
	private Integer  tcPermMin;
	
	
	

}