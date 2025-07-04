package Logica.Enums;


public enum Alimentos {
    ComidaPerro  (30, Especies.Perro  ,"Comida de Perro"  ),
    ComidaGato   (30, Especies.Gato   ,"Comida de Gato"   ),
    ComidaLoro   (20, Especies.Loro   ,"Comida de Loro"   ),
    ComidaHamster(20, Especies.Hamster,"Comida de Hamster");

    private final int valorNutritivo;
    private final Especies especies;
    private final String nombre;

    Alimentos(int precio, Especies especies, String nombre) {
        this.valorNutritivo = precio;
        this.especies = especies;
        this.nombre = nombre;
    }

    public static Alimentos getAlimento(Especies especie) {
        return switch (especie) {
            case Perro -> ComidaPerro;
            case Gato -> ComidaGato;
            case Loro -> ComidaLoro;
            case Hamster -> ComidaHamster;
            default -> throw new IllegalArgumentException("especie no es valida");
        };
    }

    public boolean esComiblePor(Especies especie) {
        return especie == this.especies;
    }

    public int getValorNutritivo() {
        return valorNutritivo;
    }

    static public Alimentos deEspecie(Especies especie) {
        if (especie == Especies.Perro) return Alimentos.ComidaPerro;
        if (especie == Especies.Gato) return Alimentos.ComidaGato;
        if (especie == Especies.Loro) return Alimentos.ComidaLoro;
        else return Alimentos.ComidaHamster;
    }
}
