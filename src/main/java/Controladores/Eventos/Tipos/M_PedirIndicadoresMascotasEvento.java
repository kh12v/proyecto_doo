package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirIndicadoresMascotasEvento extends Evento {
    public M_PedirIndicadoresMascotasEvento() {
        super(TipoEvento.PedirIndicadores, DestinoEvento.Controlador);
    }
}
