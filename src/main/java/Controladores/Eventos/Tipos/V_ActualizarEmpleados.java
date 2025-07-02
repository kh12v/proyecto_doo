package Controladores.Eventos.Tipos;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

/**
 * Anuncia un cambio en la información del modelo, para que la vista se encargue de manejarlo.
 */
public class V_ActualizarEmpleados extends Evento {
    EmpleadoState[] empleados;

    public V_ActualizarEmpleados(EmpleadoState[] empleados) {
        super(TipoEvento.ActualizarEmpleados, DestinoEvento.Vista);
        this.empleados = empleados;
    }

    public EmpleadoState[] getEstados() {
        return empleados;
    }
}
