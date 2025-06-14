package gui.Paneles.PanelTienda;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelTienda extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public PanelTienda(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new GridLayout(2, 1, 0, 0));

        PanelCliente panelCliente = new PanelCliente(ventanaPrincipal);
        PanelMenu panelMenu = new PanelMenu(ventanaPrincipal);

        add(panelCliente);
        add(panelMenu);
    }
}
