package Logica;

import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

public class Juego {
    Tienda tienda;
    ControladorPrincipal controlador;
    VentanaPrincipal ventanaPrincipal;
    EventHandler eventHandler;
    public Juego(String nombreTienda) {
        eventHandler = new EventHandler();
        tienda = new Tienda(nombreTienda);
        controlador = new ControladorPrincipal(tienda);
        ventanaPrincipal = new VentanaPrincipal("Tienda");
        controlador.enviarHandler(eventHandler);
        ventanaPrincipal.enviarHandler(eventHandler);
    }

    public void iniciar() {
        ventanaPrincipal.mostrar();
    }
}
