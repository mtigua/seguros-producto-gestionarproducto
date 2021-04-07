package seguros.producto.gestionarproducto.dto;



import java.math.BigDecimal;
import java.sql.Date;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.ProductoDo;



@Data
public class ProductoDoDto  {
	

	private Long id;
	
	private Integer doResponsable;
	
	private Boolean doplMasivo;
	
	private Date doplFecIniVta;
	
	private Integer doplPolSvs;
	
	private BigDecimal doplPorcComiSp;
	
	private BigDecimal doplPorcComiAdm;	
	
	private BigDecimal doplValorComiAdm;	
	
	private BigDecimal doplPorcAfecto;
	
	private BigDecimal doplCtocobMora1;
	
	private BigDecimal doplTopeMora1;
	
	private BigDecimal doplCtocobMora2;
	
	private BigDecimal doplTopeMora2;
		
	private BigDecimal doplPorcComiCat;
	
	private Integer doplVtaMes;
	
	private Boolean doplUsaChequeRest;	
	
	private Boolean doplAplicaSuspension;
	
	private Boolean   doplCartaMora;	
	
	private Boolean  doplCobProporcional;	

	private Boolean  doplEnvioBienvenida;	
	
	private Boolean  doplNovalidaTitCerrada;
	
	private  Long doplAQuienSeVende;
	
	@JsonIgnore 
	public ProductoDo toEntity() {
		ProductoDo productoDo = new ProductoDo();
		BeanUtils.copyProperties(this, productoDo);
		
		return productoDo;
		
	}
	
	
	
    
	
}
