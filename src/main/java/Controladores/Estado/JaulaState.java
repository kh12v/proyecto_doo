package Controladores.Estado;

import Logica.Enums.TipoContenedor;
import Logica.Jaula;

public record JaulaState(
        MascotaState mascotaState,
        TipoContenedor tipo,
        int id,
        boolean vacia
) {

    public static JaulaState toState(Jaula jaula) {
        if (jaula.estaVacia()) {
            return new JaulaState(MascotaState.NULL, jaula.getTipoContenedor(), jaula.getID(), true);
        }
        MascotaState mascota = MascotaState.toState(jaula.getMascota());
        return new JaulaState(mascota, jaula.getTipoContenedor(), jaula.getID(), false);
    }

    public int[] getIndicadores() {
        if (vacia) {
            return new int[]{};
        }
        return mascotaState.estadoIndicadores();
    }
}
