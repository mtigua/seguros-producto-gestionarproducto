package seguros.producto.gestionarproducto.entities;

import javax.persistence.Column;
import java.io.Serializable;

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
}
