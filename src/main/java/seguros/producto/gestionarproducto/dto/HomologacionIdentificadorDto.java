package seguros.producto.gestionarproducto.dto;




import lombok.Data;

@Data
public class HomologacionIdentificadorDto  {
	
	
	private Long id;	

	private String terminoOrigen;	
	
	private String terminoDestino;	
	
	private String valorOrigen;
	
	private String valorDestino;
	
	private ConceptoDto concepto;
	
	private CanalDto canal;
	
    
}
