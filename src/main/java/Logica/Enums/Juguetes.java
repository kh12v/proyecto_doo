package Logica.Enums;

public enum Juguetes {
    JuguetePerro   (50),
    JugueteGato    (50),
    JugueteLoro    (40),
    JugueteHamster (55);

    final int valorJuguete;
    Juguetes(int valorJuguete){
        this.valorJuguete = valorJuguete;
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
}
