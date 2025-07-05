package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

/**
 * Pide información actualizada de un número de mascotas del modelo.
 */
public class M_PedirMascotas extends Evento {
    /**
     * Al usar WILD, se pide información sobre todas las mascotas existentes
     */
    public static final int[] WILD = {};
    int[] indices;

    public M_PedirMascotas(int[] ids) {
        super(TipoEvento.PedirMascotas, DestinoEvento.Controlador);
        this.indices = ids;
    }

    public int[] getIDs() {
        return indices;
    }
}
