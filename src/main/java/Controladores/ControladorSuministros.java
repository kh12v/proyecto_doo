package Controladores;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Logica.Tienda;

public class ControladorSuministros implements Publicador {
    private final Tienda t;
    private EventHandler handler;
    public ControladorSuministros(Tienda t) {
        this.t = t;
    }

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
    }
}
