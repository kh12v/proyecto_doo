package Logica.Enums;

public enum Juguetes {
    JuguetePerro   (50, Especies.Perro),
    JugueteGato    (50, Especies.Gato),
    JugueteLoro    (40, Especies.Loro),
    JugueteHamster (55, Especies.Hamster);

    final int valorJuguete;
    final Especies especie;
    Juguetes(int valorJuguete, Especies especie){
        this.valorJuguete = valorJuguete;
        this.especie = especie;
    }

    public static Juguetes getJuguete(Especies especie){
        return switch (especie){
            case Especies.Perro -> JuguetePerro;
            case Especies.Gato -> JugueteGato;
            case Especies.Loro -> JugueteLoro;
            case Especies.Hamster -> JugueteHamster;
            default -> throw new IllegalArgumentException("El valor no es valido");
        };
    }

    public int getValorJuguete() {
        return valorJuguete;
    }

    public boolean esJugablePor(Especies especie) {
        return especie == this.especie;
    }

    static public Juguetes deEspecie(Especies especie) {
        if (especie == Especies.Perro) return Juguetes.JuguetePerro;
        if (especie == Especies.Gato) return Juguetes.JugueteGato;
        if (especie == Especies.Loro) return Juguetes.JugueteLoro;
        else return Juguetes.JugueteHamster;
    }
}
