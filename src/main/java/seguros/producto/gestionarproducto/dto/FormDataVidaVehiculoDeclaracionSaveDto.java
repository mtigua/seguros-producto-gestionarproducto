package seguros.producto.gestionarproducto.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class FormDataVidaVehiculoDeclaracionSaveDto {


	private Long id;

	private Integer hijoMaxEdad;
	
	private Integer edadMinimaIngreso;
	
	private Boolean  requiereFamiliar;
	
	private Boolean  requiereBeneficiarios;

	private Boolean  recalculaEdad;
	
	private Boolean conyuge;
	
	private Boolean otro;
	
	private Boolean padre;
	
	private Boolean madre;
	
	private Boolean hijo;
	
	private Boolean elMismo;
	
	private Boolean aseguradoCumulo;
	
	private BigDecimal  cumuloAporte;
	
	private Integer  mesesPermanencia;
	
	private Integer mesesCastigo;
	
	private Boolean declaracionPrincipal;
	
	private Boolean declaracionAseguradoTodos;
	
	private String planDpsTextos;
	
	private Integer  maxCantAsegurados;	
	
	private String plantillaHtmlDeclaracion1;
	
	private String plantillaHtmlDeclaracion2;
	
	private Integer vehiculoHasta;
	
	private String  antiguedadVehiculo;
	
	private Boolean  inspeccion;
	
	private Boolean controlInspeccion;
	
	private Integer  cantCuotasTbk;
	
	private Integer  cambioAuto;
	
	private Integer  primeraCuotaTbk;
	
	private Boolean  usaWs;

	private Boolean  usaWsEmision;
	
	private Integer indiqueCodigoWs;
	
	private Integer indiqueCodigoEmisionWs;
	
	private Boolean  tarifaNows;
	
	


}