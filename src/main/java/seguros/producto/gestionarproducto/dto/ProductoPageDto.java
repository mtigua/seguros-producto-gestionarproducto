package seguros.producto.gestionarproducto.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductoPageDto {
	
	
	private Long id;
	
	private String nemot;
	
	private String descrip;
	
	private Long habilitado;
	
	private CompaniaDto compania;
	
	private TipoSeguroDto tipoSeguro;
	
	private NegocioDto negocio;
	
	private RamoDto ramo;	
	
	private List<CanalDto> canales;

}