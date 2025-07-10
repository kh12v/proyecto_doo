package Logica.Enums;

public enum Producto {
    Jabon(TipoProducto.Higiene, "Jabon", 500),

    Perro(TipoProducto.Mascota, "Perro", 10000),
    Gato(TipoProducto.Mascota, "Gato", 10000),
    Loro(TipoProducto.Mascota, "Loro", 7000),
    Hamster(TipoProducto.Mascota, "Hamster", 5000),

    ComidaPerro(TipoProducto.Comida, "Comida de perro", 700),
    ComidaGato(TipoProducto.Comida, "Comida de gato", 700),
    ComidaLoro(TipoProducto.Comida, "Comida de loro", 500),
    ComidaHamster(TipoProducto.Comida, "Comida de hamster", 500),

    JuguetePerro(TipoProducto.Juguete, "Juguete de perro", 1000),
    JugueteGato(TipoProducto.Juguete, "Juguete de gato", 1000),
    JugueteLoro(TipoProducto.Juguete, "Juguete de loro", 700),
    JugueteHamster(TipoProducto.Juguete, "Juguete de hamster", 700),

    MedicamentoPerro(TipoProducto.Medicamento, "Medicamento de perro", 1000),
    MedicamentoGato(TipoProducto.Medicamento, "Medicamento de gato", 1000),
    MedicamentoLoro(TipoProducto.Medicamento, "Medicamento de loro", 1000),
    MedicamentoHamster(TipoProducto.Medicamento, "Medicamento de hamster", 1000);

    private final TipoProducto tipoProducto;
    private final String nombre;
    private final int precio;

    Producto(TipoProducto tipoProducto, String nombre, int precio) {
        this.tipoProducto = tipoProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public boolean esMascota() {
        return tipoProducto == TipoProducto.Mascota;
    }

    public Enum getEnumReal() {
        int n = (ordinal()-1) % 4;
        return switch (tipoProducto) {
            case Mascota -> Especie.values()[n];
            case Comida -> Alimentos.values()[n];
            case Medicamento -> Medicamentos.values()[n];
            case Juguete -> Juguetes.values()[n];
            case Higiene -> null;
        };
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }
}
