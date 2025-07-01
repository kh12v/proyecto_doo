package Logica;

import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Juego implements Actualizable {
    ControladorPrincipal controlador;
    VentanaPrincipal ventanaPrincipal;
    EventHandler eventHandler;

    public Juego(String nombreTienda, int dineroInicial) {
        eventHandler = new EventHandler();
        Tienda tienda = new Tienda(nombreTienda, dineroInicial);
        controlador = new ControladorPrincipal(tienda);
        ventanaPrincipal = new VentanaPrincipal(tienda, "Tienda");
        controlador.enviarHandler(eventHandler);
        ventanaPrincipal.enviarHandler(eventHandler);
    }

    public void iniciar() {
        ventanaPrincipal.mostrar();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                actualizar();
            }
        };
        timer.schedule(timerTask, Date.from(Instant.now()), 10000);
    }

    @Override
    public void actualizar() {
        controlador.actualizar();
        System.out.println(Instant.now());
    }
}
