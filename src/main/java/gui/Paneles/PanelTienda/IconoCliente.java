package gui.Paneles.PanelTienda;

import gui.Paneles.ImageLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class IconoCliente extends JPanel {
    public IconoCliente(String ruta, int ancho, int alto) {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        ImageLoader loader = ImageLoader.getInstancia();
        try {
            JPanel label = loader.cargarImagenEscalada(ancho, alto, ruta);
            add(label, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }
}
