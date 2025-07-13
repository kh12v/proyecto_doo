package Controladores.Estado;

import Logica.Enums.TipoJaula;
import Logica.Jaula;

/**
 * DTO (Data Transfer Object) de una Jaula
 * @param mascotaState estado de la mascota en la jaula
 * @param tipo específica si es una jaula grande o pequeña
 * @param id id de la jaula
 * @param vacia indica si la jaula esta vacía
 */
public record JaulaState(
        MascotaState mascotaState,
        TipoJaula tipo,
        int id,
        boolean vacia
) {

    /**
     * Método para extraer el estado de una {@code Jaula}
     * @param jaula Una jaula
     * @return Un {@code JaulaState} que la representa
     */
    public static JaulaState toState(Jaula jaula) {
        MascotaState mascota = MascotaState.toState(jaula.getMascota());
        return new JaulaState(mascota, jaula.getTipoJaula(), jaula.getID(), false);
    }


    /**
     * Regresa los indicadores de la mascota contenida
     * @return los indicadores
     */
    public int[] getIndicadores() {
        if (vacia) {
            return new int[]{};
        }
        return mascotaState.estadoIndicadores();
    }
}
