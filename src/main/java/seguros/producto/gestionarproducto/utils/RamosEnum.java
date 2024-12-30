package seguros.producto.gestionarproducto.utils;

public enum RamosEnum {

    HOGAR("HOGAR", 3L),
    VEHICULO("VEHICULO", 2L);

    private final String nombre;
    private final Long id;

    RamosEnum(String nombre, Long id) {
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