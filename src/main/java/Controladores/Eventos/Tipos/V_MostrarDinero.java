package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class V_MostrarDinero extends Evento {
    public final int dinero;

    public V_MostrarDinero(int dinero) {
        super(TipoEvento.MostrarDinero, DestinoEvento.Vista);

        this.dinero = dinero;
    }
}