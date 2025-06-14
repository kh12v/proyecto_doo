package gui.Paneles.PanelTienda;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCliente extends JPanel {
    private static final Color COLOR_DE_FONDO = new Color(87, 177, 230);

    private VentanaPrincipal ventanaPrincipal;

    public PanelCliente(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout());
        setBackground(COLOR_DE_FONDO);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        IconoCliente iconoCliente = new IconoCliente("resources/cliente.png", 300, 300, COLOR_DE_FONDO);

        add(iconoCliente, BorderLayout.SOUTH);
    }
}
