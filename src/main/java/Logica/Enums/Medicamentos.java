package Logica.Enums;

// Se utiliza el enum Productos en su lugar a lo mejor serían mejor crear clases para cada producto?
public enum Medicamentos {
    MedicinaPerro(50),
    MedicinaGato(50),
    MedicinaLoro(30),
    MedicinaHamster(35);

    final int valorMedicinal;
    Medicamentos(int valorMedicinal) {
        this.valorMedicinal = valorMedicinal;
    }

    public static Medicamentos getMedicamento(Especies especie) {
        return switch (especie) {
            case Especies.Perro -> MedicinaPerro;
            case Especies.Gato -> MedicinaGato;
            case Especies.Loro -> MedicinaLoro;
            case Especies.Hamster -> MedicinaHamster;
            default -> throw new IllegalStateException("Unexpected value: " + especie);
        };
    }

    public int valorMedicinal() {
        return valorMedicinal;
    }

    static public Medicamentos deEspecie(Especies especie) {
        if (especie == Especies.Perro) return Medicamentos.MedicinaPerro;
        if (especie == Especies.Gato) return Medicamentos.MedicinaGato;
        if (especie == Especies.Loro) return Medicamentos.MedicinaLoro;
        else return Medicamentos.MedicinaHamster;
    }
}
