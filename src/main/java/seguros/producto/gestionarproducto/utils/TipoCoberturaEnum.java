package seguros.producto.gestionarproducto.utils;

public enum TipoCoberturaEnum {

    TASA("Tasa", 1L),
    TARIFA("Tarifa", 2L),
    TRAMO("Tramo", 3L);

    private final String nombre;
    private final Long id;

    TipoCoberturaEnum(String nombre, Long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }
}