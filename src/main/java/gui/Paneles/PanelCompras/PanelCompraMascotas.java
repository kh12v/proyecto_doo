package gui.Paneles.PanelCompras;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCompraMascotas extends JPanel {
    VentanaPrincipal ventanaPrincipal;

    public PanelCompraMascotas(VentanaPrincipal ventanaPrincipal, Color colorDeFondo) {
        this.ventanaPrincipal = ventanaPrincipal;

        setBackground(colorDeFondo);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        add(new JLabel("COMPRAR MASCOTAS AQUÍ"));
    }
}
