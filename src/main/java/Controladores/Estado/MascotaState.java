package Controladores.Estado;

import Logica.Enums.Especie;
import Logica.Mascota;

import java.util.Arrays;

/**
 * DTO (Data Transfer Object) de una mascota
 *
 * @param nombre            nombre de la mascota
 * @param especie           especie de la mascota
 * @param estadoIndicadores valor de cada indicador
 */
public record MascotaState(String nombre,
                           Especie especie,
                           int[] estadoIndicadores) {
    /**
     * NULL representa la mascota nula, se usa para no usar null en JaulaState
     */
    public static final MascotaState NULL = new MascotaState("", Especie.Null, new int[]{0,0,0,0});

    /**
     * Metodo de conveniencia para extraer el estado de una mascota
     * @param mascota una mascota
     * @return el estado de la mascota
     */
    public static MascotaState toState(Mascota mascota) {
        if (mascota == null) return NULL;
        int[] indicadorEstado = mascota.getIndicadores();
        return new MascotaState(mascota.getNombre(), mascota.getEspecie(), indicadorEstado);
    }

    @Override
    public String toString() {
        return "Nombre = " + nombre + ", Especie = " + especie + ", Indicadores = " + Arrays.toString(estadoIndicadores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MascotaState s2 = (MascotaState) o;
        return nombre.equals(s2.nombre) && especie.equals(s2.especie) && Arrays.equals(estadoIndicadores, s2.estadoIndicadores);
    }

}
