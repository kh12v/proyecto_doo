package Logica.Enums;

public enum Especies {
    Perro(true,10000),
    Gato(true,10000),
    Loro(false,7000),
    Hamster(false,5000),
    Null(true,0);

    private final boolean esAnimalGrande;
    public final int precio;
    Especies(boolean esAnimalGrande, int precio) {
        this.esAnimalGrande = esAnimalGrande;
        this.precio = precio;
    }

    public boolean getEsAnimalGrande() {
        return esAnimalGrande;
    }
}
