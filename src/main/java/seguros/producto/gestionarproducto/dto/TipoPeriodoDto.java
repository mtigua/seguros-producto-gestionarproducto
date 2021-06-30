package seguros.producto.gestionarproducto.dto;


import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TipoPeriodoDto  {
	
	    private Long id;
		
		@NotBlank(message = "El campo nombre de tipo de periodo es requerido")
		private String nombre;
		
		private String descripcion;

		public TipoPeriodoDto(Long id,String nombre,
				String descripcion) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
		}

		public TipoPeriodoDto() {
			super();
		}	
	
    
}
