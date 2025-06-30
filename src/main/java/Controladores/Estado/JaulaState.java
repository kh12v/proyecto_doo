package Controladores.Estado;

import Logica.Jaula;
import Logica.TipoContenedor;

public record JaulaState(
        MascotaState mascotaState,
        int limpiezaIndicador,
        TipoContenedor tipo,
        int id
) {
    public static JaulaState toState(Jaula jaula) {
        MascotaState mascota = MascotaState.toState(jaula.getMascota());
        int limpieza = jaula.getIndicadores().getLast().getValor();
        return new JaulaState(mascota,limpieza,jaula.getTipoContenedor(),jaula.getID());
    }
}
