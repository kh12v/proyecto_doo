package gui.Paneles.PanelMascotas;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class AlmacenMascotas extends JPanel {
    private VentanaPrincipal ventanaPrincipal;

    public AlmacenMascotas(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setBackground(Color.GRAY);

        add(new Jaula(true, Jaula.Animal.GATO));
        add(new Jaula(false, Jaula.Animal.NINGUNO));
        add(new Jaula(false, Jaula.Animal.PERRO));
        add(new Jaula(false, Jaula.Animal.GATO));
        add(new Jaula(false, Jaula.Animal.LORO));
        add(new Jaula(false, Jaula.Animal.HAMSTER));
    }
}
