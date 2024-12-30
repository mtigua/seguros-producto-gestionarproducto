package seguros.producto.gestionarproducto.dto;



import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import seguros.producto.gestionarproducto.entities.TerminoCorto;


@Data
public class TerminoCortoDto  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	
	private TipoMultaDto tipoMulta;

	@NotNull(message = "El mes desde es requerido")
	private Integer mesDesde;
	
	@NotNull(message = "El mes hasta es requerido")
	private Integer mesHasta;
	
	@NotNull(message = "La prima es requerida")
	private BigDecimal primaPeriodo;
	
	@NotNull(message = "El monto es requerido")
	private BigDecimal monto;
	
	@NotNull(message = "La cuota es requerida")
	private Integer cuotas;
	
	@Size(max=5)
	private String moneda;
	
	@JsonIgnore
	public TerminoCorto toEntity() {
		TerminoCorto p = new TerminoCorto();
		BeanUtils.copyProperties(this, p);

		return p;

	}
		
	
	

	
    
}
