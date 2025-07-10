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

    public static Medicamentos getMedicamento(Especie especie) {
        return switch (especie) {
            case Especie.Perro -> MedicinaPerro;
            case Especie.Gato -> MedicinaGato;
            case Especie.Loro -> MedicinaLoro;
            case Especie.Hamster -> MedicinaHamster;
            default -> throw new IllegalStateException("Unexpected value: " + especie);
        };
    }

    static public Medicamentos deEspecie(Especie especie) {
        if (especie == Especie.Perro) return Medicamentos.MedicinaPerro;
        if (especie == Especie.Gato) return Medicamentos.MedicinaGato;
        if (especie == Especie.Loro) return Medicamentos.MedicinaLoro;
        else return Medicamentos.MedicinaHamster;
    }

    public int valorMedicinal() {
        return valorMedicinal;
    }
}
