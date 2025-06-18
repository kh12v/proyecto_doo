package gui.Paneles.PanelTienda;

import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelTienda extends JPanel {
    private VentanaPrincipal ventanaPrincipal;
    private EventHandler handler;
    private PanelCliente panelCliente;
    private PanelMenu panelMenu;
    public PanelTienda() {
        setLayout(new GridLayout(2, 1, 0, 0));

        panelCliente = new PanelCliente();
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
