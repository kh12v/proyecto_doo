package Logica;

import Logica.Enums.Alimentos;
import Logica.Enums.Especies;

import java.util.Arrays;

public class Mascota implements Actualizable {
    private final String nombre;
    private final Especies especie;
    private final int id;
    private static int idActual = 0;

    double[] indicadores;

    double[][] matrizTransicion = {
            {  0.9,     0.05,   -0.03,     0     },  // Hambre: Se reduce con el tiempo, mejora salud
            {  0.03,    0.85,    0.05,     0.03  },  // Salud: Mejora si hay hambre (alimentación)
            {  0.05,    0.05,    0.8,      0.05  },  // Felicidad: Disminuye si no hay comida
            {  0,       0.05,   -0.05,     0.9   }   // Higiene: Se reduce con el tiempo
    };
    public Mascota(String nombre, Especies especie) {
        this.nombre = nombre;
        this.especie = especie;
        this.id = idActual++;
        indicadores = new double[]{100,100,100,100};
    }

    public boolean alimentar(Alimentos alimento) {
        if (puedeComer(alimento)) {
            incrementarIndicador(Indicadores.HAMBRE,alimento.getValorNutritivo());
            return true;
        }
        return false;
    }

    public boolean puedeComer(Alimentos alimento) {
        return alimento.esComiblePor(especie);
    }

    public String getNombre() {
        return nombre;
    }

    public Especies getEspecie() {
        return especie;
    }

    public int getID() {return id;}

    public int[] getIndicadores(){
        return Arrays.stream(indicadores).mapToInt(i -> (int) i).toArray();
    }

    @Override
    public void actualizar() {
        actualizarIndicadores();
    }
    public void actualizarIndicadores() {
        double[] nuevosIndicadores = new double[4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                nuevosIndicadores[i] += matrizTransicion[i][j]*indicadores[j];
            }
            nuevosIndicadores[i] = Math.min(nuevosIndicadores[i],100);
        }
        indicadores = nuevosIndicadores;
    }
    private void incrementarIndicador(Indicadores indicador, double cantidad) {
        indicadores[indicador.ordinal()] = Math.min(indicadores[indicador.ordinal()] + cantidad,100);
    }
}

enum Indicadores{
    HAMBRE,
    SALUD,
    FELICIDAD,
    HIGIENE
}

