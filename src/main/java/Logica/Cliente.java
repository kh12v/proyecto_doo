package Logica;

import Logica.Enums.Especies;

import java.util.Arrays;
import java.util.Random;

public class Cliente {
    private final Especies mascota;
    double calificacion = 3.0;

    public Cliente(Especies mascota, double sesgo) {
        this.mascota = mascota;
        if (sesgo >= 0.5 && sesgo < 1.0) {
            calificacion *= sesgo;
        }
    }

    public static Cliente clienteAleatorio(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        double sesgo = random.nextDouble()/2 + 0.5;
        Especies mascota = Especies.values()[random.nextInt(Especies.values().length-1)];
        return new Cliente(mascota, sesgo);
    }
    public boolean entregarMascota(Mascota mascota) {
        if (mascota.getEspecie() != this.mascota) {
            calificacion *= 0.5;
            return false;
        }
        int[] indicadores = mascota.getIndicadores();
        Arrays.stream(indicadores).forEach(indicador -> calificacion *= ((double) indicador / 100 + 0.5));
        calificacion = Math.min(calificacion, 5.0);
        return true;
    }

    public double getCalificacion() {
        return Math.min(calificacion, 5.0);
    }

    public Especies getEspeciePedida() {
        return mascota;
    }
}
