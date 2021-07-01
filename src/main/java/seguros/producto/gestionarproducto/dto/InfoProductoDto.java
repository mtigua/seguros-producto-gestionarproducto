package seguros.producto.gestionarproducto.dto;


import lombok.Data;



@Data
public class InfoProductoDto  {
	
	    private Long id;
	    
	    private String compania;

	    private Long companiaId;

	    private Long negocioId;

		private String negocio;
		
		private String ramo;
		
		private String nemotecnico;
		
		private TipoRamoDto tipoRamo;

		private String plan;

		private Long idTipoCompania;
		

}
