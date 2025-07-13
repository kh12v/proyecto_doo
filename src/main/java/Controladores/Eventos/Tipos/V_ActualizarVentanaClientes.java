package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class V_ActualizarVentanaClientes extends Evento {
    public V_ActualizarVentanaClientes() {
        super(TipoEvento.ActualizarVentanaClientes, DestinoEvento.Vista);
    }
}
