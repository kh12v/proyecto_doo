import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import Logica.Actualizable;
import Logica.Tienda;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que guarda todas las clases usadas por el programa.
 */
public class Juego implements Actualizable {
    ControladorPrincipal controlador;
    VentanaPrincipal ventanaPrincipal;
    EventHandler eventHandler;

    public Juego(int dineroInicial) {
        String nombreTienda = JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre de su tienda",
                "Nombre de la tienda",
                JOptionPane.PLAIN_MESSAGE);
        eventHandler = new EventHandler();
        Tienda tienda = new Tienda(nombreTienda,dineroInicial);
        controlador = new ControladorPrincipal(tienda);
        ventanaPrincipal = new VentanaPrincipal(nombreTienda);
        controlador.enviarHandler(eventHandler);
        ventanaPrincipal.enviarHandler(eventHandler);
    }

    /**
     * Inicializa el juego, echando a correr el {@code Timer}
     */
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
