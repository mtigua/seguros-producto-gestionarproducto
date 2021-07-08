package seguros.producto.gestionarproducto.entities;


import lombok.Data;

import seguros.producto.gestionarproducto.entities.keys.GrupoMejorOfertaKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "grupos_mejor_oferta")
@Data
public class GruposMejorOferta {

	@EmbeddedId
	private GrupoMejorOfertaKey id;

	@Column(name = "id_producto", insertable = false, updatable = false)
	private Long  productoId;

	@Column(name = "id_oferta", insertable = false, updatable = false)
	private Long  ofertaId;

}
