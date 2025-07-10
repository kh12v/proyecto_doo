package Logica.Enums;

public enum Juguetes {
    JuguetePerro(50, Especie.Perro),
    JugueteGato(50, Especie.Gato),
    JugueteLoro(40, Especie.Loro),
    JugueteHamster(55, Especie.Hamster);

    final int valorJuguete;
    final Especie especie;

    Juguetes(int valorJuguete, Especie especie) {
        this.valorJuguete = valorJuguete;
        this.especie = especie;
    }

    public static Juguetes getJuguete(Especie especie) {
        return switch (especie) {
            case Especie.Perro -> JuguetePerro;
            case Especie.Gato -> JugueteGato;
            case Especie.Loro -> JugueteLoro;
            case Especie.Hamster -> JugueteHamster;
            default -> throw new IllegalArgumentException("El valor no es valido");
        };
    }

    static public Juguetes deEspecie(Especie especie) {
        if (especie == Especie.Perro) return Juguetes.JuguetePerro;
        if (especie == Especie.Gato) return Juguetes.JugueteGato;
        if (especie == Especie.Loro) return Juguetes.JugueteLoro;
        else return Juguetes.JugueteHamster;
    }

    public int getValorJuguete() {
        return valorJuguete;
    }

    public boolean esJugablePor(Especie especie) {
        return especie == this.especie;
    }
}
