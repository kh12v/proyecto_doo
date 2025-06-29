package Logica;

import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

public class Juego {
    Tienda tienda;
    ControladorPrincipal controlador;
    VentanaPrincipal ventanaPrincipal;
    EventHandler eventHandler;
    public Juego(String nombreTienda, int dineroInicial) {
        eventHandler = new EventHandler();
        tienda = new Tienda(nombreTienda, dineroInicial);
        controlador = new ControladorPrincipal(tienda);
        ventanaPrincipal = new VentanaPrincipal(tienda, "Tienda");
        controlador.enviarHandler(eventHandler);
        ventanaPrincipal.enviarHandler(eventHandler);
    }

    public void iniciar() {
        ventanaPrincipal.mostrar();
    }
}
