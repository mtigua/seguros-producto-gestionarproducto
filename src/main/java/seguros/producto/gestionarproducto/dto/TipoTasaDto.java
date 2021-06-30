package seguros.producto.gestionarproducto.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class TipoTasaDto {

	    private Long id;
		
		@NotBlank(message = "El campo nombre de tipo de tasa es requerido")
		private String nombre;
		
		private String descripcion;

		public TipoTasaDto(Long id, String nombre,
				String descripcion) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
		}

		public TipoTasaDto() {
			super();
		}	

		
}
