package gui.Paneles.PanelCompras;

import gui.Paneles.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Muestra la imagen de un producto (las imágenes se almacenan en \src\main\resources)
 */
public class ImagenProducto extends JPanel {
    private final static int ANCHO = 150;
    private final static int ALTO = 150;

    public ImagenProducto(String ruta, Color colorDeFondo) {
        setBackground(colorDeFondo);
        setVisible(true);

        ImageLoader imageLoader = ImageLoader.getInstancia();
        try {
            JPanel panel = imageLoader.cargarImagenEscalada(ANCHO, ALTO, ruta);
            add(panel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
