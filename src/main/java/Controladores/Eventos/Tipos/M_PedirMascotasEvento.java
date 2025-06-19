package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

/**
 * Pide información actualizada de un número de mascotas del modelo.
 */
public class M_PedirMascotasEvento extends Evento {
    // me encantaría usar una máscara de bits para esto :(
    int[] indices;
    /**
     * Al usar WILD, se pide información sobre todas las mascotas existentes
     */
    public static final int[] WILD = {};
    public M_PedirMascotasEvento(int[] indices) {
        super(TipoEvento.PedirMascotas, DestinoEvento.Controlador);
        this.indices = indices;
    }
    public int[] getIDs() {
        return indices;
    }
}
