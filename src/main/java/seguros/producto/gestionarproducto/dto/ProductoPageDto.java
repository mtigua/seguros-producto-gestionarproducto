package seguros.producto.gestionarproducto.dto;

import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.Producto;

@Data
public class ProductoPageDto {
	
	
	private Long id;
	
	private String nemot;
	
	private String descrip;
	
	private CompaniaDto compania;
	
	private TipoSeguroDto tipoSeguro;
	
	private NegocioDto negocio;
	
	private RamoDto ramo;	
	
	
	@JsonIgnore 
	public Producto toEntity() {
		Producto producto = new Producto();
		BeanUtils.copyProperties(this, producto);
		
		
		return producto;
		
	}
	

}