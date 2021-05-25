package seguros.producto.gestionarproducto.dto;



import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.TerminoCorto;


@Data
public class TerminoCortoSaveDto  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	private Integer cuotas;	
	
	@NotNull(message = "La mes hasta es requerida")
	@Range(min = 1, max = 12)
	private Integer mesHasta;	
	
	private BigDecimal primaPeriodo;
	
	private BigDecimal monto;
	
	@NotNull(message = "La mes desde es requerida")
	@Range(min = 1, max = 12)
	private Integer mesDesde;	
	
	@Size(max=5)
	private String moneda;
	
	private Long tipoMulta;
	
	@JsonIgnore
	public TerminoCorto toEntity() {
		TerminoCorto p = new TerminoCorto();
		BeanUtils.copyProperties(this, p);

		return p;

	}
		

	
    
}
