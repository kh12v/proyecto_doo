package gui.Paneles.PanelEmpleados;

import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Muestra una imágen de un empleado
 */
public class ImagenEmpleado extends JPanel {
    private final static Color COLOR_DE_FONDO = Color.GRAY;
    private final static int ANCHO = 150;
    private final static int ALTO = 150;

    private final static String RUTA = "/empleado.png";

    public ImagenEmpleado() {
        setBackground(COLOR_DE_FONDO);
        setLayout(new OverlayLayout(this));
        setVisible(true);
        ImageLoader loader = ImageLoader.getInstancia();
        try {
            JPanel panel = loader.cargarImagenEscalada(ANCHO, ALTO, RUTA);
            add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
