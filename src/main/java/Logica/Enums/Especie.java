package Logica.Enums;

public enum Especie {
    Perro(true, 10000),
    Gato(true, 10000),
    Loro(false, 7000),
    Hamster(false, 5000),
    Null(true, 0);

    public final int precio;
    private final boolean esAnimalGrande;

    Especie(boolean esAnimalGrande, int precio) {
        this.esAnimalGrande = esAnimalGrande;
        this.precio = precio;
    }

    public boolean getEsAnimalGrande() {
        return esAnimalGrande;
    }
}
