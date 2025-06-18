package gui.Paneles.PanelEmpleados;

import gui.Paneles.VentanaPrincipal;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEmpleados extends JPanel {
    VentanaPrincipal ventanaPrincipal;

    private final static Color COLOR_DE_FONDO = Color.GRAY;

    public PanelEmpleados(VentanaPrincipal ventanaPrincipal) {
        setBackground(COLOR_DE_FONDO);
        setLayout(new GridLayout(0, 3));

        for (int i = 0; i < 3; i++) {
            add(new PanelEmpleado(ventanaPrincipal, COLOR_DE_FONDO));
        }
    }
}

