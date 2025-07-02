package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class M_PedirDinero extends Evento {
    public M_PedirDinero() {
        super(TipoEvento.PedirDinero, DestinoEvento.Controlador);
    }
}
