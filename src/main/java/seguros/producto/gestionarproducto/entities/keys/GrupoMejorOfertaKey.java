package seguros.producto.gestionarproducto.entities.keys;

import javax.persistence.Column;
import java.io.Serializable;

public class GrupoMejorOfertaKey implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_oferta")
    private Long idOferta;

    public GrupoMejorOfertaKey(Long idProducto, Long idOferta) {
    	this.idProducto =  idProducto;
    	this.idOferta = idOferta;
    }

    public GrupoMejorOfertaKey() {
    	
    }
}
