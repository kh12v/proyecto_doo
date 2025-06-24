package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

/**
 * Pide información actualizada de un número de empleados del modelo.
 */
public class M_PedirEmpleadosEvento extends Evento {
    int[] indices;
    /**
     * Al usar WILD, se pide información sobre todos los empleados existentes
     */
    public static final int[] WILD = {};
    public M_PedirEmpleadosEvento(int[] indices) {
        super(TipoEvento.PedirEmpleados, DestinoEvento.Controlador);
        this.indices = indices;
    }
    public int[] getIDs() {
        return indices;
    }
}
