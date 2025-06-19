package Controladores;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Evento;
import Controladores.Eventos.Publicador;
import Logica.Tienda;

public class ControladorPrincipal implements Publicador {
    private Tienda tienda;
    private ControladorMascotas mascotas;
    private ControladorSuministros suministros;
    private EventHandler handler;

    public ControladorPrincipal(Tienda tienda) {
        this.tienda = tienda;
        mascotas = new ControladorMascotas(tienda);
        suministros = new ControladorSuministros(tienda);
    }

    public void enviarHandler(EventHandler handler) {
        mascotas.enviarHandler(handler);
        suministros.enviarHandler(handler);
        this.handler = handler;
    }
}
