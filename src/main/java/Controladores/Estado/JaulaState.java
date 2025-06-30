package Controladores.Estado;

import Logica.Jaula;
import Logica.TipoContenedor;

public record JaulaState(
        MascotaState mascotaState,
        int limpiezaIndicador,
        TipoContenedor tipo,
        int id,
        boolean vacia
) {

    public static JaulaState toState(Jaula jaula) {
        if (jaula.estaVacia()) {
            return new JaulaState(MascotaState.NULL,-1,jaula.getTipoContenedor(), jaula.getID(), true);
        }
        MascotaState mascota = MascotaState.toState(jaula.getMascota());
        int limpieza = jaula.getIndicadores().getLast().getValor();
        return new JaulaState(mascota,limpieza,jaula.getTipoContenedor(),jaula.getID(),false);
    }

    public int[] getIndicadores(){
        if(vacia){return new int[]{};}
        int[] indicadores = new int[4];
        int[] indicadoresMascota = mascotaState.estadoIndicadores();
        System.arraycopy(indicadoresMascota, 0, indicadores, 0, 3);
        indicadores[3] = limpiezaIndicador;
        return indicadores;
    }
}
