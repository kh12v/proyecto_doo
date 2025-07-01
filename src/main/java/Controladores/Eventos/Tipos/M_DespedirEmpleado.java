package Controladores.Eventos.Tipos;

import Controladores.Estado.EmpleadoState;
import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;
import Logica.TipoContenedor;

public class M_DespedirEmpleado extends Evento {
    public EmpleadoState estado;

    public M_DespedirEmpleado(EmpleadoState estado) {
        super(TipoEvento.DespedirEmpleado, DestinoEvento.Controlador);

        this.estado = estado;
    }
}
