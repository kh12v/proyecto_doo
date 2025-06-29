package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class V_MostrarMensaje extends Evento {
    public final String mensaje;

    public V_MostrarMensaje(String mensaje) {
        super(TipoEvento.MostrarMensaje, DestinoEvento.Vista);

        this.mensaje = mensaje;
    }
}
