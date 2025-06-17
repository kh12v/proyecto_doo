package gui.Paneles.PanelMascotas;

import gui.Paneles.VentanaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelIndicador extends JPanel {
    private JProgressBar crearIndicador(int ancho, String texto) {
        JProgressBar indicador = new JProgressBar(0, 100);
        indicador.setPreferredSize(new Dimension(ancho, 25));
        indicador.setValue(100);
        indicador.setStringPainted(true);
        indicador.setString(texto);
        indicador.setBorder(new EmptyBorder(0, 0, 0, 0));

        return indicador;
    }

    public PanelIndicador(int ancho, Color colorDeFondo) {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(colorDeFondo);
        // hambre
        // salud
        // higiene
        // felicidad

        Box box = Box.createVerticalBox();

        box.add(crearIndicador(ancho, "Hambre"));
        box.add(crearIndicador(ancho, "Salud"));
        box.add(crearIndicador(ancho, "Higiene"));
        box.add(crearIndicador(ancho, "Felicidad"));

        add(box);
    }
}
