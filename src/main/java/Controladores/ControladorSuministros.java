package Controladores;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Logica.Tienda;

public class ControladorSuministros implements Controlador {
    private final Tienda t;
    private EventHandler handler;
    public ControladorSuministros(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }

    @Override
    public void recibir(Evento evento) {

    }

    @Override
    public DestinoEvento[] getEventosEscuchados() {
        return new DestinoEvento[]{DestinoEvento.Controlador};
    }
}
