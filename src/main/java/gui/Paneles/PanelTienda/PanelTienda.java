package gui.Paneles.PanelTienda;

import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Publicador;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

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

    public void enviarHandler(EventHandler handler) {
        this.handler = handler;
        panelCliente.enviarHandler(handler);
        panelMenu.enviarHandler(handler);
    }
}
