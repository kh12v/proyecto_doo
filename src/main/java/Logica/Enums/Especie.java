package Logica.Enums;

public enum Especie {
    Perro(true),
    Gato(true),
    Loro(false),
    Hamster(false),
    Null(true);

    private final boolean esAnimalGrande;

    Especie(boolean esAnimalGrande) {
        this.esAnimalGrande = esAnimalGrande;
    }

    public boolean getEsAnimalGrande() {
        return esAnimalGrande;
    }
}
