package seguros.producto.gestionarproducto.entities.keys;

import javax.persistence.Column;

import lombok.Data;

import java.io.Serializable;

@Data
public class CriterioKey implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_profesion")
    private Long idProfesion;
    
	@Column(name = "id_pregunta")
	private Long idPregunta;

    public CriterioKey(Long idProducto, Long idProfesion, Long idPregunta) {
    	this.idProducto =  idProducto;
    	this.idProfesion = idProfesion;
    	this.idPregunta = idPregunta;
    }

    public CriterioKey() {
    	
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof CriterioKey)) return false;
        CriterioKey pk = (CriterioKey) obj;
        return pk.idProducto == idProducto && pk.idProfesion == idProfesion && pk.idPregunta == idPregunta;
    }
    
    public int hashCode() {
        return (int) (idProducto+idProfesion+idPregunta);
    }
    
}
