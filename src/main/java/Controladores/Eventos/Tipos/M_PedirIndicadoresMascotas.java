package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirIndicadoresMascotas extends Evento {
    public M_PedirIndicadoresMascotas() {
        super(TipoEvento.PedirIndicadores, DestinoEvento.Controlador);
    }
}
