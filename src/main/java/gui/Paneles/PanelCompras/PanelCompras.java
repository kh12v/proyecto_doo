package gui.Paneles.PanelCompras;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelCompras extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PanelCompras(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(1, 2));

        add(new PanelCompraMascotas(ventanaPrincipal, COLOR_DE_FONDO));
        add(new PanelCompraSuministros(ventanaPrincipal, COLOR_DE_FONDO));
    }
}
