package Logica;

import java.util.Arrays;

public class Cliente {
    private final Especies mascota;
    double calificacion = 3.0;

    public Cliente(Especies mascota, double sesgo) {
        this.mascota = mascota;
        if (sesgo > 0.0 && sesgo < 1.0) {
            calificacion *= sesgo;
        }
    }

    public boolean entregarMascota(Mascota mascota) {
        if(mascota.getEspecie() != this.mascota){
            calificacion *= 0.5;
            return false;
        }
        int[] indicadores = mascota.getIndicadores();
        Arrays.stream(indicadores).forEach(indicador -> calificacion *= ((double) indicador /100 + 0.5));
        calificacion = Math.max(calificacion, 5.0);
        return true;
    }

    public double getCalificacion() {
        return calificacion;
    }

}
