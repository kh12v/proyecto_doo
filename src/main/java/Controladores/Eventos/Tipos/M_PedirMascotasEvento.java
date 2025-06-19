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
    boolean forzar;
    /**
     * Al usar WILD, se pide información sobre todas las mascotas existentes
     */
    public static final int[] WILD = {Integer.MIN_VALUE};
    public M_PedirMascotasEvento(int[] indices, boolean forzar) {
        super(TipoEvento.PedirMascotas, DestinoEvento.Controlador);
        this.indices = indices;
        this.forzar = forzar;
    }
    public boolean isForzar(){
        return forzar;
    }
    public int[] getIndices() {
        return indices;
    }
}
