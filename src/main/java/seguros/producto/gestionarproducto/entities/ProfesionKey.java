package seguros.producto.gestionarproducto.entities;

import javax.persistence.Column;
import java.io.Serializable;

public class ProfesionKey implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_profesion")
    private Long idProfesion;

    public ProfesionKey(Long idProducto, Long idProfesion) {
    	this.idProducto =  idProducto;
    	this.idProfesion = idProfesion;
    }

    public ProfesionKey() {
    	
    }
}
