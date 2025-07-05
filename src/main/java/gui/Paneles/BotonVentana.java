package gui.Paneles;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import Controladores.Eventos.Tipos.V_CambiarVentana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BotonVentana extends JButton implements Publicador {
    private static final int ANCHO = 150;
    private static final int ALTO = 100;
    private EventHandler handler;
    private final Ventanas ventana;

    public BotonVentana(String texto, Ventanas ventana) {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setMinimumSize(new Dimension(ANCHO, ALTO));
        setMaximumSize(new Dimension(ANCHO, ALTO));
        switch (ventana) {
            case TIENDA -> setMnemonic(KeyEvent.VK_ESCAPE);
            default -> setMnemonic(ventana.name().charAt(0));
        }
        setText(texto);
        this.ventana = ventana;
    }

    @Override
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        addActionListener(e -> handler.enviar(new V_CambiarVentana(ventana)));
    }
}
