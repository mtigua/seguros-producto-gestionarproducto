package seguros.producto.gestionarproducto.dto;


import java.sql.Date;
import lombok.Data;

@Data
public class FormDataTraspasoSaveDto {


	private Long id;

	private Long tipoTraspaso;
	
	private String docuNroPoliza;
	
	private String docuDigPoliza;
	
	private Long tipoAcreedor;
	
	private String  nroPolizaMinimo;
	
	private String  nroPolizaMaximo;
	
	private Long tipoFacturar;
	
	private Date docuFecTerVigencia;
	
	private String persCodigoAcre;
	
	private String persDigitoAcre;
	
	private String persCodigoFactSingle;
	
	private String persDigitoFactSingle;
	
	

}