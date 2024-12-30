package seguros.producto.gestionarproducto.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class CoberturaProductoKey implements Serializable {
 	
	private static final long serialVersionUID = 1L;
	
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "id_cobertura")
    private Long idCobertura;
    
    public CoberturaProductoKey(Long idProducto, Long idCobertura) {
    	this.idProducto =  idProducto;
    	this.idCobertura = idCobertura;
    }

    public CoberturaProductoKey() {
    	
    }
}
