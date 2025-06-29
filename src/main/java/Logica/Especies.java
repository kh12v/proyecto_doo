package Logica;

public enum Especies {
    Perro(true),
    Gato(true),
    Loro(false),
    Hamster(false),
    NullGrande(true),
    NullPequeno(false);

    private boolean esAnimalGrande;

    Especies(boolean esAnimalGrande) {
        this.esAnimalGrande = esAnimalGrande;
    }

    public boolean getEsAnimalGrande() {
        return esAnimalGrande;
    }
}
