package Logica;

import Logica.Enums.Alimentos;
import Logica.Enums.Especies;
import Logica.Enums.Juguetes;
import Logica.Enums.Medicamentos;

import java.util.Arrays;

public class Mascota implements Actualizable {
    public static int I_HAMBRE = 0;
    public static int I_SALUD = 1;
    public static int I_FELICIDAD = 2;
    public static int I_HIGIENE = 3;
    private static int idActual = 0;
    private final String nombre;
    private final Especies especie;
    private final int id;
    private final double[][] matrizTransicion = {
            {0.9, 0.05, -0.03, 0},  // Hambre: Se reduce con el tiempo, mejora salud
            {0.03, 0.85, 0.05, 0.03},  // Salud: Mejora si hay hambre (alimentación)
            {0.05, 0.05, 0.8, 0.05},  // Felicidad: Disminuye si no hay comida
            {0, 0.05, -0.05, 0.9}   // Higiene: Se reduce con el tiempo
    };
    double[] indicadores;

    public Mascota(String nombre, Especies especie) {
        this.nombre = nombre;
        this.especie = especie;
        this.id = idActual++;
        indicadores = new double[]{100, 100, 100, 100};
    }

    public boolean alimentar(Alimentos alimento) {
        if (puedeComer(alimento)) {
            incrementarIndicador(I_HAMBRE, alimento.getValorNutritivo());
            return true;
        }
        return false;
    }

    public boolean jugar(Juguetes juguete) {
        if (puedeJugar(juguete)) {
            incrementarIndicador(I_FELICIDAD, juguete.getValorJuguete());
            return true;
        }
        return false;
    }

    public void limpiar() {
        incrementarIndicador(I_HIGIENE, 100);
    }

    public boolean darMedicamento(Medicamentos medicamento) {
        if (Medicamentos.getMedicamento(this.especie) == medicamento) {
            incrementarIndicador(I_SALUD, medicamento.valorMedicinal());
            return true;
        }
        return false;
    }

    public boolean puedeComer(Alimentos alimento) {
        return alimento.esComiblePor(especie);
    }

    public boolean puedeJugar(Juguetes juguete) {
        return juguete.esJugablePor(especie);
    }

    public String getNombre() {
        return nombre;
    }

    public Especies getEspecie() {
        return especie;
    }

    public int getID() {
        return id;
    }

    public int[] getIndicadores() {
        return Arrays.stream(indicadores).mapToInt(i -> (int) i).toArray();
    }

    @Override
    public void actualizar() {
        actualizarIndicadores();
    }

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

    private void incrementarIndicador(int indicador, double cantidad) {
        indicadores[indicador] = Math.min(indicadores[indicador] + cantidad, 100);
    }
}


