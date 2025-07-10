package Logica;

import Logica.Enums.Alimentos;
import Logica.Enums.Especie;
import Logica.Enums.Juguetes;
import Logica.Enums.Medicamentos;

import java.util.Arrays;

/**
 * Representa a una mascota con nombre y especie específicos, que guarda su alimentación, felicidad, salud e higiene.
 */
public class Mascota implements Actualizable {
    public static int I_HAMBRE = 0;
    public static int I_SALUD = 1;
    public static int I_FELICIDAD = 2;
    public static int I_HIGIENE = 3;
    private final String nombre;
    private final Especie especie;
    private final double[][] matrizTransicion = {
            {0.9, 0.05, -0.03, 0},     // Hambre: Se reduce con el tiempo, mejora salud
            {0.03, 0.85, 0.05, 0.03},  // Salud: Mejora si hay hambre (alimentación)
            {0.05, 0.05, 0.8, 0.05},   // Felicidad: Disminuye si no hay comida
            {0, 0.05, -0.05, 0.9}      // Higiene: Se reduce con el tiempo
    };
    double[] indicadores;

    public Mascota(String nombre, Especie especie) {
        this.nombre = nombre;
        this.especie = especie;
        indicadores = new double[]{100, 100, 100, 100};
    }

    /**
     * Alimenta a la {@code Mascota} con un tipo de {@code Alimento} si es que la {@code Mascota} lo puede comer.
     * @param alimento el {@code Alimento} entregado
     * @return {@code true} si se pudo alimentar, {@code false} si no
     */
    public boolean alimentar(Alimentos alimento) {
        if (puedeComer(alimento)) {
            incrementarIndicador(I_HAMBRE, alimento.getValorNutritivo());
            return true;
        }
        return false;
    }

    /**
     * Entrega un {@code Juguete} a la {@code Mascota} para que juegue con él.
     * @param juguete el {@code Juguete} entregado
     * @return {@code true} si el juguete es apropiado, {@code false} si no
     */
    public boolean jugar(Juguetes juguete) {
        if (puedeJugar(juguete)) {
            incrementarIndicador(I_FELICIDAD, juguete.getValorJuguete());
            return true;
        }
        return false;
    }

    /**
     * Limpia a la {@code Mascota}.
     */
    public void limpiar() {
        incrementarIndicador(I_HIGIENE, 100);
    }

    /**
     * Cura a la {@code Mascota} con un {@code Medicamento}.
     * @param medicamento el {@code Medicamento} entregado
     * @return {@code true} si el {@code Medicamento} sirve, {@code false} si no
     */
    public boolean darMedicamento(Medicamentos medicamento) {
        if (Medicamentos.getMedicamento(this.especie) == medicamento) {
            incrementarIndicador(I_SALUD, medicamento.valorMedicinal());
            return true;
        }
        return false;
    }

    /**
     * Verifica si el tipo de {@code Alimento} es comible por la mascota.
     * @param alimento el tipo de {@code Alimento} a verificar
     * @return {@code true} si el {@code Alimento} es comible, {@code false} si no
     */
    public boolean puedeComer(Alimentos alimento) {
        return alimento.esComiblePor(especie);
    }

    /**
     * Verifica si la {@code Mascota} puede jugar con el tipo de {@code Juguete}.
     * @param juguete el tipo de {@code Juguete} a verificar
     * @return {@code true} si la {@code Mascota} puede jugar con él {@code Juguete}, {@code false} si no
     */
    public boolean puedeJugar(Juguetes juguete) {
        return juguete.esJugablePor(especie);
    }

    /**
     * Regresa el nombre de la {@code Mascota}.
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa la {@code Especie} de la {@code Mascota}.
     * @return la {@code Especie}
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * Regresa los indicadores de la {@code Mascota}.
     * @return un arreglo con los indicadores
     */
    public int[] getIndicadores() {
        return Arrays.stream(indicadores).mapToInt(i -> (int) i).toArray();
    }

    @Override
    public void actualizar() {
        actualizarIndicadores();
    }

    /**
     * Actualiza los indicadores de la {@code Mascota}, multiplicando el vector indicadores por la matriz de indicadores.
     */
    public void actualizarIndicadores() {
        double[] nuevosIndicadores = new double[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nuevosIndicadores[i] += matrizTransicion[i][j] * indicadores[j];
            }
            nuevosIndicadores[i] = Math.min(nuevosIndicadores[i], 100);
        }
        indicadores = nuevosIndicadores;
    }

    /**
     * Incrementa un indicador por una cantidad fija.
     * @param indicador el índice del indicador
     * @param cantidad la cantidad a aumentar
     */
    private void incrementarIndicador(int indicador, double cantidad) {
        indicadores[indicador] = Math.min(indicadores[indicador] + cantidad, 100);
    }

    /**
     * Fija el indicador a un valor.
     * @param indicador el índice del indicador
     * @param cantidad la cantidad a aumentar
     */
    public void setIndicador(int indicador, double cantidad) {
        indicadores[indicador] = cantidad;
    }

    /**
     * Obtiene un indicador específico
     * @param indicador el índice del indicador
     * @return el indicador
     */
    public int getIndicador(int indicador) {
        return getIndicadores()[indicador];
    }
}


