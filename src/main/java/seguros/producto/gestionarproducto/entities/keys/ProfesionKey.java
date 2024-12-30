package seguros.producto.gestionarproducto.entities.keys;

import javax.persistence.Column;

import lombok.Data;

import java.io.Serializable;

@Data
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
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProfesionKey)) return false;
        ProfesionKey pk = (ProfesionKey) obj;
        return pk.idProducto == idProducto && pk.idProfesion == idProfesion;
    }
    
    public int hashCode() {
        return (int) (idProducto+idProfesion);
    }
    
}
