package gui.Paneles;

import gui.Paneles.PanelTienda.PanelTienda;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    static private final int ANCHO = 950;
    static private final int ALTO = 950;

    private JPanel panelPrincipal = null;

    public VentanaPrincipal(String titulo) {
        setTitle(titulo);
        setSize(new Dimension(ANCHO, ALTO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPanelPrincipal(new PanelTienda(this));

        setLocationRelativeTo(null);
    }

    public void setPanelPrincipal(JPanel panel) {
        if (panelPrincipal != null) {
            panelPrincipal.removeAll();
            remove(panelPrincipal);
        }

        panelPrincipal = panel;
        add(panelPrincipal);

        revalidate();
        repaint();
    }

    public void mostrar() {
        setVisible(true);
    }
}
