package Logica;

public enum Especies {
    Perro(true),
    Gato(true),
    Loro(false),
    Hamster(false),
    Null(false);

    private boolean esAnimalGrande;

    Especies(boolean esAnimalGrande) {
        this.esAnimalGrande = esAnimalGrande;
    }

    public boolean getEsAnimalGrande() {
        return esAnimalGrande;
    }
}
