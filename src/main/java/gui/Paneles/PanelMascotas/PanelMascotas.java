package gui.Paneles.PanelMascotas;

import gui.Paneles.PanelTienda.PanelTienda;
import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMascotas extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PanelMascotas(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            add(new PanelMascota(ventanaPrincipal, Color.GRAY));
        }
    }
}
