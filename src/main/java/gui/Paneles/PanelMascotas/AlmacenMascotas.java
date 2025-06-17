package gui.Paneles.PanelMascotas;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class AlmacenMascotas extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public AlmacenMascotas(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setBackground(Color.GRAY);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            add(new PanelMascota(ventanaPrincipal, Color.GRAY, i));
            // add(new Jaula(true, Jaula.Animal.NINGUNO));
        }
    }
}
