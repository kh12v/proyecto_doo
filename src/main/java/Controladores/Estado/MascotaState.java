package Controladores.Estado;

import Logica.Especies;
import Logica.Mascota;

import java.util.Arrays;

/**
 * DTO (Data Transfer Object) de una mascota
 * @param nombre nombre de la mascota
 * @param especie especie de la mascota
 * @param estadoIndicadores estado de cada indicador, osea valor actual y frecuencia de reduccion
 */
public record MascotaState (String nombre,
                           Especies especie,
                           int id,
                           int[][] estadoIndicadores){
    /**
     * DEL representa la mascota nula, si se recibe esta mascota en un índice, significa que hay que borrarla
     */
    public static final MascotaState DEL = new MascotaState("BORRAR",Especies.NullGrande,0, null);
    public static MascotaState toState(Mascota mascota){
        int[][] indicadorEstado = mascota
                .getIndicadores()
                .stream()
                .map(m -> new int[]{m.getValor(),m.getFrecuenciaReduccion()})
                .toArray(int[][]::new);
        return new MascotaState(mascota.getNombre(),mascota.getEspecie(),mascota.getID(), indicadorEstado);
    }

    @Override
    public String toString() {
        return "Nombre = " + nombre + ", Especie = " + especie + ", Indicadores = " + Arrays.deepToString(estadoIndicadores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MascotaState s2 = (MascotaState) o;
        return nombre.equals(s2.nombre) && especie.equals(s2.especie) && Arrays.deepEquals(estadoIndicadores, s2.estadoIndicadores);
    }

}
