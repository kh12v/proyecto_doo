package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirCliente extends Evento {
    public M_PedirCliente() {
        super(TipoEvento.PedirCliente, DestinoEvento.Controlador);
    }
}
