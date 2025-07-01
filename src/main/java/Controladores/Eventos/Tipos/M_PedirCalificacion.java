package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirCalificacion extends Evento {
    public M_PedirCalificacion() {
        super(TipoEvento.PedirCalificacion, DestinoEvento.Controlador);
    }
}
