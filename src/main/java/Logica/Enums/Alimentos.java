package Logica.Enums;


/**
 * Representa tipos de alimentos, los cuales puede consumir solo una especie, y poseen un valor nutritivo
 */
public enum Alimentos {
    ComidaPerro(30, Especie.Perro, "Comida de Perro"),
    ComidaGato(30, Especie.Gato, "Comida de Gato"),
    ComidaLoro(20, Especie.Loro, "Comida de Loro"),
    ComidaHamster(20, Especie.Hamster, "Comida de Hamster");

    private final int valorNutritivo;
    private final Especie especie;
    private final String nombre;

    Alimentos(int precio, Especie especie, String nombre) {
        this.valorNutritivo = precio;
        this.especie = especie;
        this.nombre = nombre;
    }


    /**
     * Obtiene el {@code Alimento} que le corresponde a una {@code Especie}.
     * @param especie la {@code Especie}
     * @return el {@code Alimento}
     */
    public static Alimentos deEspecie(Especie especie) {
        if (especie == Especie.Perro) return Alimentos.ComidaPerro;
        if (especie == Especie.Gato) return Alimentos.ComidaGato;
        if (especie == Especie.Loro) return Alimentos.ComidaLoro;
        else return Alimentos.ComidaHamster;
    }

    /**
     * Verifica si una {@code Especie} puede comer él {@code Alimento}.
     * @param especie la {@code Especie}
     * @return {@code true} si al especie puede comerlo, {@code false} si no
     */
    public boolean esComiblePor(Especie especie) {
        return especie == this.especie;
    }

    /**
     * Obtiene el valor nutritivo del {@code Alimento}
     * @return el valor nutritivo
     */
    public int getValorNutritivo() {
        return valorNutritivo;
    }
}
