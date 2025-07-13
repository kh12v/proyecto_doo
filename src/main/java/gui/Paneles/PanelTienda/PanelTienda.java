package gui.Paneles.PanelTienda;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * Panel principal de la tienda
 */
public class PanelTienda extends JPanel implements Publicador {
    private VentanaPrincipal ventanaPrincipal;
    private EventHandler handler;
    private final PanelCliente panelCliente;
    private final PanelMenu panelMenu;

    public PanelTienda(String nombre) {
        setLayout(new GridLayout(2, 1, 0, 0));

        panelCliente = new PanelCliente(nombre);
        panelMenu = new PanelMenu();

        add(panelCliente);
        add(panelMenu);
    }

    /**
     * Permite enviar eventos
     * @param handler: Objeto encargado de enviar eventos a los objetos suscriptores
     * @see Publicador
     * @see Controladores.Eventos.Suscriptor
     */
    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        panelCliente.enviarHandler(handler);
        panelMenu.enviarHandler(handler);
    }
}
