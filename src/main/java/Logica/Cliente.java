package Logica;

import Logica.Enums.Especie;

import java.util.Arrays;
import java.util.Random;

/**
 * Representa a alguien que desea comprar una {@code Mascota} de una {@code Especie}, el {@code Cliente} guarda una calificación
 * de la atención recibida, la cual entrega al finalizar la compra.
 */
public class Cliente {
    private final Especie mascota;
    double calificacion = 3.0;

    public Cliente(Especie mascota, double sesgo) {
        this.mascota = mascota;
        if (sesgo >= 0.5 && sesgo < 1.0) {
            calificacion *= sesgo;
        }
    }

    /**
     * Genera un {@code Cliente} con valores aleatorios
     * @return El {@code Cliente}
     */
    public static Cliente clienteAleatorio(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        double sesgo = random.nextDouble()/2 + 0.5;
        Especie mascota = Especie.values()[random.nextInt(Especie.values().length-1)];
        return new Cliente(mascota, sesgo);
    }


    /**
     * Le entrega una mascota al {@code Cliente}, el cual evalúa si esta cumple con la especie pedida,
     * de cumplir verifica el estado de la mascota, cambiando su calificación basándose en que tan bien mantenida está
     * @param mascota La mascota que se le entregará al cliente
     * @return {@code true} si la mascota es de la especie requerida, {@code false} si no
     */
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


    /**
     * Regresa la calificación actual sobre la atención recibida
     * @return La calificación
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * Regresa la {@code Especie} de mascota que desea el {@code Cliente}
     * @return La {@code Especie}
     */
    public Especie getEspeciePedida() {
        return mascota;
    }
}
