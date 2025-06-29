package Logica;

public enum Producto {
    Perro("Perro", 10000),
    Gato("Gato", 10000),
    Loro("Loro", 7000),
    Hamster("Hamster", 5000),

    JuguetePerro("Juguete de perro", 1000),
    JugueteGato("Juguete de gato", 1000),
    JugueteLoro("Juguete de loro", 700),
    JugueteHamster("Juguete de hamster", 700),

    MedicamentoPerro("Medicamento de perro", 1000),
    MedicamentoGato("Medicamento de gato", 1000),
    MedicamentoLoro("Medicamento de loro", 1000),
    MedicamentoHamster("Medicamento de hamster", 1000);

    private String nombre;
    private int precio;

    Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
}
