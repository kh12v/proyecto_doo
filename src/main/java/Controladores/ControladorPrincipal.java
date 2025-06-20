package Controladores;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Logica.Tienda;

public class ControladorPrincipal implements Controlador {
    private final ControladorMascotas mascotas;
    private final ControladorSuministros suministros;
    private EventHandler handler;

    public ControladorPrincipal(Tienda tienda) {
        mascotas = new ControladorMascotas(tienda);
        suministros = new ControladorSuministros(tienda);
    }

    public void enviarHandler(EventHandler handler) {
        mascotas.enviarHandler(handler);
        suministros.enviarHandler(handler);
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
