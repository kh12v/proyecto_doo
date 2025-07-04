package Controladores.Estado;

import Logica.Enums.Especies;
import Logica.Mascota;

import java.util.Arrays;

/**
 * DTO (Data Transfer Object) de una mascota
 * @param nombre nombre de la mascota
 * @param especie especie de la mascota
 * @param estadoIndicadores valor de cada indicador
 */
public record MascotaState (String nombre,
                           Especies especie,
                           int id,
                           int[] estadoIndicadores){
    /**
     * DEL representa la mascota nula, si se recibe esta mascota en un índice, significa que hay que borrarla
     */
    public static final MascotaState DEL = new MascotaState("BORRAR",Especies.Null,0, null);
    public static final MascotaState NULL = new MascotaState("",Especies.Null,0, null);
    public static MascotaState toState(Mascota mascota){
        if (mascota==null) return null;
        int[] indicadorEstado = mascota.getIndicadores();
        return new MascotaState(mascota.getNombre(),mascota.getEspecie(),mascota.getID(), indicadorEstado);
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
